package com.project.chawchaw.controller;

import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.repository.FollowRepository;
import com.project.chawchaw.response.CommonResult;
import com.project.chawchaw.service.FollowService;
import com.project.chawchaw.service.ResponseService;
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
    private final ResponseService responseService;


    @PostMapping("/follow/{userId}")
    public ResponseEntity follow(@PathVariable("userId")Long toUserId, @RequestHeader(value ="Authorization")String token){

        followService.follow(toUserId,token);
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.FOLLOW,true),HttpStatus.CREATED);

    }
    @DeleteMapping("follow/{userId}")
    public ResponseEntity unFollow(@PathVariable("userId")Long toUserId,@RequestHeader(value="Authorization")String token){
        followService.unFollow(toUserId,token);
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.UNFOLLOW,true),HttpStatus.CREATED);

    }
}
