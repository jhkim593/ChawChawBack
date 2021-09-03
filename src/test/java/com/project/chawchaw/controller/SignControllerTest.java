package com.project.chawchaw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chawchaw.dto.user.UserLoginRequestDto;
import com.project.chawchaw.dto.user.UserSignUpRequestDto;
import com.project.chawchaw.service.SignService;
import com.project.chawchaw.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SignControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SignService signService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;



    
    @Test
    public void sendEmail()throws Exception{

        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        params.add("email","fpdlwjzlr@naver.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/mail/send")
                .params(params));

    }
    /**
      페이스북 로그인
        소셜 가입 안되어있을 때
     * */
    @Test
    public void socialLogin1()throws Exception{

        UserLoginRequestDto userLoginRequestDto=new UserLoginRequestDto(null,null,"facebook",
                null,"EAALhcr1cpyIBAOZAQZCZB5ZBphaKg8ds4VZBXV3BTlv3GKkxPiYyR15bClU8u8MZCKL9gwL4tsRmRL6MDYKkEVTXv2eUmQk6G2NQKZBSehVvTVbIj3CLU00VIIKjx0Ttn1fhu9ADD6recu3HNyc1ij7DXZAQtqkp8boz88IZBduOCSZCc5L4PphJGmVvx1MCHGp9N7TGbjjLFmT29Fgm2c3X8rAt0ZCuQ7AG0MeYpbyhU2JyqZCWeSLGUlPL",
                "2934138203530054");


        String content = objectMapper.writeValueAsString(userLoginRequestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(MockMvcResultHandlers.print());
    }
    /**
     소셜 가입이 되어있을 때**/
    @Test
    public void socialLogin2()throws Exception{
       //given

        UserSignUpRequestDto userSignUpRequestDto=new UserSignUpRequestDto();
        userSignUpRequestDto.setEmail("2934138203530054");
        userSignUpRequestDto.setName("김진현");
        userSignUpRequestDto.setPassword("2934138203530054");
        userSignUpRequestDto.setWeb_email("32141221@dankook.ac.kr");
        userSignUpRequestDto.setSchool("서울시립대학교");
        userSignUpRequestDto.setProvider("facebook");
        signService.signup(userSignUpRequestDto);
        em.flush(); em.clear();
       //when ,then
        UserLoginRequestDto userLoginRequestDto=new UserLoginRequestDto(null,null,"facebook",
                null,"EAALhcr1cpyIBAOZAQZCZB5ZBphaKg8ds4VZBXV3BTlv3GKkxPiYyR15bClU8u8MZCKL9gwL4tsRmRL6MDYKkEVTXv2eUmQk6G2NQKZBSehVvTVbIj3CLU00VIIKjx0Ttn1fhu9ADD6recu3HNyc1ij7DXZAQtqkp8boz88IZBduOCSZCc5L4PphJGmVvx1MCHGp9N7TGbjjLFmT29Fgm2c3X8rAt0ZCuQ7AG0MeYpbyhU2JyqZCWeSLGUlPL",
                "2934138203530054");


        String content = objectMapper.writeValueAsString(userLoginRequestDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(MockMvcResultHandlers.print());


    }

}