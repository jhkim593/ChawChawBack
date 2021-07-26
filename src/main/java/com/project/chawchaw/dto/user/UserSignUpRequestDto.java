package com.project.chawchaw.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String web_email;

    private String school;

    private String content;
    private List<String> country=new ArrayList<>();
    private List<String> language=new ArrayList<>();
    private List<String> hopeLanguage=new ArrayList<>();
    private String facebookUrl;
    private String instagramUrl;

}
