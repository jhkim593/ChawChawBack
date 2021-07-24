package com.project.chawchaw.dto;

import com.project.chawchaw.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class UserDto {

    private String name;
    private String imageUrl;
    private String content;
    private List<String> country=new ArrayList<>();
    private List<String> language=new ArrayList<>();
    private List<String> hopeLanguage=new ArrayList<>();
    private String facebookUrl;
    private String instagramUrl;
    private Long view;
    private int follows;

 public UserDto(User user,int follows){
     this.name=user.getName();
     this.imageUrl=user.getImageUrl();
     this.content=user.getContent();
     this.facebookUrl=user.getFacebookUrl();
     this.instagramUrl=user.getInstagramUrl();
     this.view=user.getViews();
     this.follows=follows;

     this.hopeLanguage=user.getHopeLanguage().stream().map(hl->hl.getHopeLanguage().getName()).collect(Collectors.toList());
     this.country=user.getCountry().stream().map(c->c.getCountry().getName()).collect(Collectors.toList());
     this.language=user.getLanguage().stream().map(l->l.getLanguage().getName()).collect(Collectors.toList());

 }

}
