package com.project.chawchaw.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserUpdateDto {
    private List<String> country;
    private List<String> language;
    private List<String> hopeLanguage;
    private String content;
    private String facebookUrl;
    private String instagramUrl;
    private String imageUrl;
}
