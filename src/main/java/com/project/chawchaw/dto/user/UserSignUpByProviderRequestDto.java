package com.project.chawchaw.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignUpByProviderRequestDto {

    private String email;

    private String name;
    private String imageUrl;
    private String web_email;
    private String school;

    private String content;
    private List<String> country;
    private List<String> language;
    private List<String> hopeLanguage;
    private String facebookUrl;
    private String instagramUrl;
    private String repCountry;
    private String repLanguage;
    private String repHopeLanguage;

}
