package com.project.chawchaw.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserSignUpRequestDto {

//  @Pattern(regexp = "/^0-9a-zA-Z@0-9a-zA-Z\\.[a-zA-Z]{2,3}$/i")
    private String email;
//  @Pattern(regexp = "/^.(?=^.{8,15}$)(?=.\\d)(?=.[a-zA-Z])(?=.[!@#$%^&+=]).*$/",message = "비밀번호 형식은 특수문자/문자/숫자 조합 8 ~ 15 글자 입니다.")
    private String password;
    private String name;
//  @Pattern(regexp = "/^0-9a-zA-Z@([-_\\.]?[0-9a-zA-Z]).ac*.kr$/i")
    private String web_email;

    private String school;
    private String imageUrl;
    private String provider;
//    private String content;
//    private List<String> country=new ArrayList<>();
//    private List<String> language=new ArrayList<>();
//    private List<String> hopeLanguage=new ArrayList<>();
//    private String facebookUrl;
//    private String instagramUrl;
//    private String repCountry;
//    private String repLanguage;
//    private String repHopeLanguage;

}
