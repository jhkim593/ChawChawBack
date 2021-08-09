package com.project.chawchaw.dto.user;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
