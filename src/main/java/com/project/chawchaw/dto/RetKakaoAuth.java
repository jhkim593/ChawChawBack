package com.project.chawchaw.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetKakaoAuth {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
    private long refresh_token_expires_in;

}