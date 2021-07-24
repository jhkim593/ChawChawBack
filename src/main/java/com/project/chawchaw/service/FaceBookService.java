package com.project.chawchaw.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.chawchaw.dto.FaceBookProfile;
import com.project.chawchaw.dto.KakaoProfile;
import com.project.chawchaw.dto.RetKakaoAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RequiredArgsConstructor
@Service
public class FaceBookService {

    private final RestTemplate restTemplate;
    private final Environment env;
    private final Gson gson;


    public FaceBookProfile getFaceBookProfile(String accessToken, String userId) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("fields", "id,name,email");
        params.add("access_token", accessToken);



        UriComponents uri = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/"+userId).queryParam("fields", "id,name,email").queryParam("access_token", accessToken).build();

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params);
        ResponseEntity<String> response = restTemplate.getForEntity(
                uri.toUri(),

                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        FaceBookProfile profile  =null;

        try {
            profile = objectMapper.readValue(response.getBody(), FaceBookProfile.class);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return profile;

    }
}