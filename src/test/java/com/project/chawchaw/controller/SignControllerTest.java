package com.project.chawchaw.controller;

import com.project.chawchaw.service.SignService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
    
    
    @Test
    public void sendEmail()throws Exception{
       //given
        MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
        params.add("email","fpdlwjzlr@naver.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/send-email")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
                .params(params));


       
       //when
       
       //then
    }

}