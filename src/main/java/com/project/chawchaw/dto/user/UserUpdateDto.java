package com.project.chawchaw.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateDto {
    private List<String> country;
    private List<String> language;
    private List<String> hopeLanguage;
    private String content;
    private String facebookUrl;
    private String instagramUrl;
    private String imageUrl;
    private String repCountry;
    private String repLanguage;
    private String repHopeLanguage;
}
