package com.project.chawchaw.controller;

import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.repository.FollowRepository;
import com.project.chawchaw.response.CommonResult;
import com.project.chawchaw.service.FollowService;
import com.project.chawchaw.service.ResponseService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = {"2.follow"})
public class FollowController {

    private final FollowService followService;
    private final ResponseService responseService;


    @PostMapping("/follow/{userId}")
    public CommonResult follow(@PathVariable("userId")Long toUserId,Long id){

        followService.follow(toUserId,id);
        return responseService.getSuccessResult();

    }
    @DeleteMapping("follow/{userId}")
    public CommonResult unFollow(@PathVariable("userId")Long toUserId,@RequestHeader(value="X-AUTH-TOKEN")String token){
        followService.unFollow(toUserId,token);
        return responseService.getSuccessResult();
    }
}
