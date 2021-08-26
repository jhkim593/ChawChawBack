package com.project.chawchaw.controller;

import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.exception.AuthenticationEntryPointException;
import com.project.chawchaw.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import 생략

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {

        throw new AuthenticationEntryPointException();
    }

    @GetMapping(value = "/entrypoint/expired")
    public ResponseEntity expiredTokenException() {

        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.EXPIRED_TOKEN,false), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }
}