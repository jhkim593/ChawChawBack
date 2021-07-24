package com.project.chawchaw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpByProviderRequestDto {

    private String name;
    private String web_email;
    private String school;
    private String imageUrl;
    private String content;
    private String country;
    private String language;
    private String hopeLanguage;
    private String socialUrl;

}
