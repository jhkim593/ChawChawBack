package com.project.chawchaw.controller;

import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.dto.*;
import com.project.chawchaw.entity.CustomUserDetails;
import com.project.chawchaw.response.CommonResult;
import com.project.chawchaw.response.ListResult;
import com.project.chawchaw.response.SingleResult;
import com.project.chawchaw.service.ResponseService;
import com.project.chawchaw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3.user"})
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "단일 회원 조회",notes = "다른유저의 단일 회원 정보를 조회한다.")
    @PostMapping(value = "/users/{userId}")
    public SingleResult<UserDto> userDetail(@PathVariable("userId") Long userId){
        return responseService.getSingResult(userService.detailUser(userId));

    }

    @ApiOperation(value = "본인 프로필 조회",notes = "자신의 프로필 정보를 조회한다.")
    @PostMapping(value = "/users/{userId}/profile")
    public SingleResult<UserProfileDto> userProfile(@PathVariable("userId") Long userId){
       return responseService.getSingResult(userService.userProfile(userId));

    }

    @ApiOperation(value = "프로필 수정",notes = "자신의 프로필 정보를 수정한다.")
    @PutMapping(value = "/users/profile")
    public CommonResult userProfileUpdate( @ModelAttribute UserUpdateDto userUpdateDto,
                                            @RequestHeader("X-AUTH-TOKEN")String token){

    userService.userProfileUpdate(userUpdateDto,Long.valueOf(jwtTokenProvider.getUserPk(token)));
   return responseService.getSuccessResult();


    }
    @ApiOperation(value = "전체 회원 조회",notes = "검색조건에 맞는 전체회원을 조회한다.")
    @PostMapping(value = "/users")
    public ListResult<UsersDto> users(@ModelAttribute UserSearch userSearch, String school){

        return responseService.getListResult(userService.users(userSearch,school));


    }


//    @ApiOperation(value = "프로필 정보 삭제",notes = "자신의 프로필 정보를 삭제한다.")
//    @DeleteMapping (value = "/users/{content}")
//    public CommonResult userProfileDelete(@PathVariable("content")String content, @ModelAttribute UserUpdateDto userUpdateDto,
//                                            @AuthenticationPrincipal CustomUserDetails customUserDetails){
//
//        userService.userProfileDelete(content,userUpdateDto,1L);
//        return responseService.getSuccessResult();
//
//    }



}
