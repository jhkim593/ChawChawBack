package com.project.chawchaw.controller;

import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.dto.user.*;
import com.project.chawchaw.service.ResponseService;
import com.project.chawchaw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3.user"})
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "단일 회원 조회",notes = "다른유저의 단일 회원 정보를 조회한다.")
    @PostMapping(value = "/users/{userId}")
    public ResponseEntity userDetail(@PathVariable("userId") Long userId){

        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.detailUser(userId)),HttpStatus.OK);

    }

    @ApiOperation(value = "본인 프로필 조회",notes = "자신의 프로필 정보를 조회한다.")
    @PostMapping(value = "/users/{userId}/profile")
    public ResponseEntity<UserProfileDto> userProfile(@PathVariable("userId") Long userId){
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.userProfile(userId)),HttpStatus.OK);


    }

    @ApiOperation(value = "프로필 수정",notes = "자신의 프로필 정보를 수정한다.")
    @PutMapping(value = "/users/profile")
    public ResponseEntity userProfileUpdate( @ModelAttribute UserUpdateDto userUpdateDto,
                                            @RequestHeader("X-AUTH-TOKEN")String token){

    userService.userProfileUpdate(userUpdateDto,Long.valueOf(jwtTokenProvider.getUserPk(token)));
   return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.UPDATE_USER,true),HttpStatus.OK);


    }
    @ApiOperation(value = "전체 회원 조회",notes = "검색조건에 맞는 전체회원을 조회한다.")
    @PostMapping(value = "/users")
    public ResponseEntity users(@ModelAttribute UserSearch userSearch, String school){


        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.users(userSearch,school)),HttpStatus.OK);

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
