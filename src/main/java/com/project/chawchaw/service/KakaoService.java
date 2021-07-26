package com.project.chawchaw.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.chawchaw.dto.social.KakaoProfile;
import com.project.chawchaw.dto.social.RetKakaoAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;


    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    public KakaoProfile getKakaoProfile(String accessToken) {
        System.out.println("=============================================");
        System.out.println(accessToken);
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " +accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");





        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>( headers);

//        try {
//            // Request profile
//            ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.profile"), request, String.class);
//            if (response.getStatusCode() == HttpStatus.OK)
//                return gson.fromJson(response.getBody(), KakaoProfile.class);
//        } catch (Exception e) {
//            throw new CommunicationException();
//        }
//        throw new CommunicationException();

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                request,
                String.class
        );
      ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile profile  =null;

        try {
            profile = objectMapper.readValue(response.getBody(), KakaoProfile.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public RetKakaoAuth getKakaoTokenInfo(String code) {
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Set parameter
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", baseUrl + kakaoRedirect);
        params.add("code", code);
        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(env.getProperty("spring.social.kakao.url.token"), request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), RetKakaoAuth.class);
        }
        return null;
    }

    public String logoutKakao(String accessToken){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " +accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>( headers);



        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v1/user/logout",
                HttpMethod.POST,
                request,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();

        String id=null;
        try {
            id = objectMapper.readValue(response.getBody(), String.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return id;



    }
}