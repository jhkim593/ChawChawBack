package com.project.chawchaw.service;

import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.entity.Follow;
import com.project.chawchaw.entity.User;
import com.project.chawchaw.exception.FollowAlreadyException;
import com.project.chawchaw.exception.FollwNotFoundException;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.FollowRepository;
import com.project.chawchaw.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public void follow(Long toUserId, String token) {
        Long fromUserId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        if(followRepository.findByFollow(fromUserId,toUserId).isPresent()){
            throw new FollowAlreadyException();
        }
        User fromUser = userRepository.findById(fromUserId).orElseThrow(UserNotFoundException::new);
        User toUser = userRepository.findById(toUserId).orElseThrow(UserNotFoundException::new);
        System.out.println();
        followRepository.save(Follow.createFollow(fromUser, toUser));

    }

    public void unFollow(Long toUserId, String token) {

        Long fromUserId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        Follow follow = followRepository.findByFollow(fromUserId, toUserId).orElseThrow(FollwNotFoundException::new);
        followRepository.delete(follow);
    }
}