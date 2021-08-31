package com.project.chawchaw.controller;

import com.project.chawchaw.config.jwt.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.service.FollowService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = {"2.follow"})
public class FollowController {

    private final FollowService followService;
    private final JwtTokenProvider jwtTokenProvider;



    @PostMapping("/follow/{userId}")
    public ResponseEntity follow(@PathVariable("userId")Long toUserId, @RequestHeader(value ="Authorization")String token){

        Long fromUserId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        followService.follow(toUserId,fromUserId);
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.FOLLOW,true),HttpStatus.CREATED);

    }
    @DeleteMapping("/follow/{userId}")
    public ResponseEntity unFollow(@PathVariable("userId")Long toUserId,@RequestHeader(value="Authorization")String token){
        Long fromUserId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        followService.unFollow(toUserId,fromUserId);
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.UNFOLLOW,true),HttpStatus.CREATED);

    }
}
