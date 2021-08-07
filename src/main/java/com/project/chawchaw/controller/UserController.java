package com.project.chawchaw.controller;


import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.dto.FileUploadResponse;
import com.project.chawchaw.dto.user.*;
import com.project.chawchaw.entity.CustomUserDetails;
import com.project.chawchaw.service.ResponseService;
import com.project.chawchaw.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3.user"})
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${file.path}")
    private String fileRealPath;

    @Value("${file.defaultImage}")
    private String defaultImage;

    @ApiOperation(value = "단일 회원 조회",notes = "다른유저의 단일 회원 정보를 조회한다.")
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity userDetail(@PathVariable("userId") Long userId,
//                                     @AuthenticationPrincipal CustomUserDetails customUserDetails

    @RequestHeader("Authorization")String token){


        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.detailUser(userId, Long.valueOf(jwtTokenProvider.getUserPk(token))))
                ,HttpStatus.OK);

    }

    @ApiOperation(value = "본인 프로필 조회",notes = "자신의 프로필 정보를 조회한다.")
    @GetMapping(value = "/users/profile")
    public ResponseEntity<UserProfileDto> userProfile(@RequestHeader("Authorization")String token){
        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.userProfile(Long.valueOf(jwtTokenProvider.getUserPk(token)))),HttpStatus.OK);


    }

    @ApiOperation(value = "프로필 수정",notes = "자신의 프로필 정보를 수정한다.")
    @PostMapping(value = "/users/profile")
    public ResponseEntity userProfileUpdate( @ModelAttribute UserUpdateDto userUpdateDto,
                                            @RequestHeader("Authorization")String token){


        userService.userProfileUpdate(userUpdateDto,Long.valueOf(jwtTokenProvider.getUserPk(token)));
   return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.UPDATE_USER,true),HttpStatus.OK);


    }
    @ApiOperation(value = "전체 회원 조회",notes = "검색조건에 맞는 전체회원을 조회한다.")
    @GetMapping(value = "/users")
    public ResponseEntity users(@ModelAttribute UserSearch userSearch, String school){


        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.READ_USER,true,
                userService.users(userSearch,school)),HttpStatus.OK);

    }

    @ApiOperation(value = "이미지 업로드",notes = "자신의 프로필 이미지를 업로드 한다.")
    @PostMapping(value = "/users/image")
    public ResponseEntity profileImageUpload(@RequestParam("file") MultipartFile file,@RequestHeader("Authorization") String token){

            String imageUrl = userService.fileUpload(file, Long.valueOf(jwtTokenProvider.getUserPk(token)));
            if(imageUrl.isEmpty()){

                return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.IMAGE_UPLOAD_FAIL, false), HttpStatus.OK);


            }
            else {
                return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.IMAGE_UPLOAD_SUCCESS, true,
                        imageUrl), HttpStatus.OK);

            }

//         String s=userService.fileUpload(file, Long.valueOf(jwtTokenProvider.getUserPk(token)));


//        String fileUri = ServletUriComponentsBuilder.fromPath("https://chawchaw-service.best")
//                .path("/users/image?imageUrl=")
//                .path(fileName)
//                .toUriString();

//        return s;


    }
    @GetMapping("/users/image")
    public ResponseEntity getUserImage(@RequestParam String imageUrl,@RequestHeader("Authorization")String token) {
        byte[] result = null;
        HttpHeaders header = new HttpHeaders();

        try {
            File file = new File(fileRealPath + File.separator + imageUrl);
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = FileCopyUtils.copyToByteArray(file);

        } catch (Exception e) {
            return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.IMAGE_GET_FAIL, false), HttpStatus.OK);
        }
        return new ResponseEntity(result, header, HttpStatus.OK);
    }







    @ApiOperation(value = "프로필 이미지 삭제",notes = "업로드된 프로필 이미지를 삭제한다.")
    @DeleteMapping(value = "/users/image")
    public ResponseEntity profileImageDelete( @RequestHeader("Authorization") String token){
            if( userService.deleteImage(Long.valueOf(jwtTokenProvider.getUserPk(token)))){
                return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.IMAGE_DELETE_SUCCESS, true,defaultImage), HttpStatus.OK);
            }
            else{
                return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.IMAGE_DELETE_FAIL,false), HttpStatus.OK);

            }

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
