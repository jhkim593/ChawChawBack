package com.project.chawchaw.dto;

import com.project.chawchaw.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserProfileDto {

    private String email;
    private String name;
    private String web_email;
    private String school;
    private String imageUrl;
    private String content;
    private List<String> country;
    private List<String> language;
    private List<String> hopeLanguage;
    private String facebookUrl;
    private String instagramUrl;

    public UserProfileDto(User user){
        this.email=user.getEmail();
        this.name=user.getName();
        this.web_email=user.getWeb_email();
        this.school=user.getSchool();
        this.imageUrl=user.getImageUrl();
        this.content=user.getContent();
        this.facebookUrl=user.getFacebookUrl();
        this.instagramUrl=user.getInstagramUrl();
        this.hopeLanguage=user.getHopeLanguage().stream().map(hl->hl.getHopeLanguage().getName()).collect(Collectors.toList());
        this.country=user.getCountry().stream().map(c->c.getCountry().getName()).collect(Collectors.toList());
        this.language=user.getLanguage().stream().map(l->l.getLanguage().getName()).collect(Collectors.toList());

    }
}
