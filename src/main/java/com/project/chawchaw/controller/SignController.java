package com.project.chawchaw.controller;

import com.project.chawchaw.dto.UserLoginRequestDto;
import com.project.chawchaw.dto.UserSignUpByProviderRequestDto;
import com.project.chawchaw.dto.UserSignUpRequestDto;
import com.project.chawchaw.response.CommonResult;
import com.project.chawchaw.response.SingleResult;
import com.project.chawchaw.dto.UserLoginResponseDto;
import com.project.chawchaw.service.ResponseService;
import com.project.chawchaw.service.SignService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Api(tags = {"1.sign"})
public class SignController {


    private final SignService signService;
    private final ResponseService responseService;





//    @ApiOperation(value = "메일 인증번호 전송", notes = "가입 할 이메일에 인증코드 전송")
//    @PostMapping("/users/send-email") // 이메일 인증 코드 보내기
//    public CommonResult emailAuth(@RequestParam String email) throws Exception {
//        signService.sendSimpleMessage(email);
//
//        return responseService.getSuccessResult();
//    }
//
//
//    //인증 번호를 db에 저장할지 따로 쿠키에 저장할지
//    @ApiOperation(value = "인증번호 판별", notes = "입력 받은 인증번호를 판별한다.")
//
//    @PostMapping("/users/verification-email") // 이메일 인증 코드 검증
//    public CommonResult verifyCode(@RequestParam String code,@RequestParam String code2) {
//        if (code2.equals(code)) {
//            return responseService.getSuccessResult();
//        } else {
//            return responseService.getFailResult();
//        }
//    }


    @ApiOperation(value = "회원가입",notes = "회원가입")
    @PostMapping(value = "/users/signup",headers = "content-type=multipart/form-data")
    public CommonResult signup( @ModelAttribute UserSignUpRequestDto requestDto){
        signService.signup(requestDto);
        return responseService.getSuccessResult();

    }


    @ApiOperation(value = "중복회원조회",notes = "중복회원조회")
    @PostMapping(value = "/users/email/duplicate")
    public SingleResult<Boolean> signup(String email){
       return responseService.getSingResult(signService.userCheck(email));


    }


    @ApiOperation(value = "로그인",notes = "로그인")
    @PostMapping(value = "/users/login")
    public SingleResult<UserLoginResponseDto> login(@ModelAttribute UserLoginRequestDto requestDto
    ){
       return  responseService.getSingResult(signService.login(requestDto));


    }


    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/users/login/{provider}")
    public SingleResult<UserLoginResponseDto> loginByProvider(
            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        return responseService.getSingResult(signService.loginByProvider(accessToken,provider));

    }
//    @ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
//    @PostMapping(value = "/users/signup/{provider}")
//    public CommonResult signUpByProvider(@PathVariable("provider") String provider,
//                                         @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken,
//                                         @ModelAttribute UserSignUpByProviderRequestDto requestDto) {
//
//          signService.signUpByProvider(requestDto,accessToken,provider);
//          return responseService.getSuccessResult();
//    }


    @ApiOperation(value = "로그아웃", notes = "로그아웃을 한다")
    @PostMapping(value = "/users/logout")
    public CommonResult logout(@RequestHeader(value="" +
            "") String token) {

        signService.logoutMember(token);
        return responseService.getSuccessResult();
    }
    @ApiOperation(value = "회원탈퇴", notes = "회원탈퇴를 한다")
    @DeleteMapping (value = "/users")
    public CommonResult userDelete(@RequestHeader(value="X-AUTH-TOKEN") String token) {

        signService.userDelete(token);
        return responseService.getSuccessResult();
    }


}