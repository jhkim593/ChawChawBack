package com.project.chawchaw.repository.follow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chawchaw.dto.chat.ChatMessageDto;
import com.project.chawchaw.dto.chat.MessageType;
import com.project.chawchaw.dto.follow.FollowAlarmDto;
import com.project.chawchaw.dto.follow.FollowType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Repository
public class FollowAlarmRepository {
    private final RedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    public void createFollowAlarm(FollowAlarmDto followAlarmDto,Long toUserId){
       String key=null;
        if(followAlarmDto.getFollowType().equals(FollowType.FOLLOW)){

            key =toUserId.toString()+":follow"+ "_" + UUID.randomUUID().toString();

        }
        if(followAlarmDto.getFollowType().equals(FollowType.UNFOLLOW)){

            key =toUserId.toString()+":unfollow"+ "_" + UUID.randomUUID().toString();

        }
        if(key!=null)
        redisTemplate.opsForValue().set(key,followAlarmDto);
    }
//    public List<ChatMessageDto> findChatMessageByRoomId(Long toUserId){
//        Set<String> keys = redisTemplate.keys(roomId.toString()+"_"+"*");
//
//        List<ChatMessageDto>chatMessageDtos=new ArrayList<>();
//        for(String key:keys){
//
//            ChatMessageDto chatMessageDto = objectMapper.convertValue(redisTemplate.opsForValue().get(key), ChatMessageDto.class);
//            chatMessageDtos.add(chatMessageDto);
//        }
//
//        Collections.sort(chatMessageDtos, (c1, c2)-> {
//
//            return c2.getRegDate().compareTo(c1.getRegDate());
//        });
//
//        return chatMessageDtos.stream()
////                .limit(20)
//                .collect(Collectors.toList());
//
//    }
//
}
