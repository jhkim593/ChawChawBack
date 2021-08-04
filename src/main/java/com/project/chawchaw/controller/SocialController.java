package com.project.chawchaw.controller;

import com.google.gson.Gson;
import com.project.chawchaw.dto.social.FaceBookProfile;
import com.project.chawchaw.dto.social.KakaoProfile;
import com.project.chawchaw.service.FaceBookService;
import com.project.chawchaw.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


// import 생략

@RequiredArgsConstructor
@Controller

public class SocialController {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final KakaoService kakaoService;
    private final FaceBookService faceBookService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    /**
     * 카카오 로그인 페이지
     */
    @GetMapping("/social/login")
        public ModelAndView socialLogin(ModelAndView mav) {

            StringBuilder loginUrl = new StringBuilder()
                    .append(env.getProperty("spring.social.kakao.url.login"))
                    .append("?client_id=").append(kakaoClientId)
                    .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

        mav.addObject("loginUrl", loginUrl);
        mav.setViewName("social/login");
        return mav;
    }


    /**
     * 카카오 인증 완료 후 리다이렉트 화면
     */
    @ResponseBody
    @GetMapping(value = "/social/login/{provider}")
    public String redirectKakao( @RequestParam String code,@PathVariable("provider")String provider) {


        return code;
    }

    @ResponseBody
    @GetMapping(value = "/login/kakao")
    public String loginKakao( @RequestParam String code) throws Exception {
//        KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(kakaoService.getKakaoTokenInfo(code).getAccess_token());
//        System.out.println(kakaoProfile.getKakao_account());
        String token = kakaoService.getKakaoTokenInfo(code).getAccess_token();
        KakaoProfile kakaoProfile=kakaoService.getKakaoProfile(token);
        System.out.println(kakaoProfile.getEmail());
        System.out.println(kakaoProfile.getName());
        System.out.println(kakaoProfile.getImageUrl());

        return "ok";
    }


    @ResponseBody
    @GetMapping("/login/facebook")
    public String facebook(String accessToken,String userId){

        FaceBookProfile faceBookProfile = faceBookService.getFaceBookProfile(accessToken, userId);
        System.out.println(faceBookProfile.getEmail());
        System.out.println(faceBookProfile.getImageUrl());
        System.out.println(faceBookProfile.getName());
    return "ok";

    }


    @ResponseBody
    @GetMapping("")
    public String facebook1(){
        return "hi";
    }

}