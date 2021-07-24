package com.project.chawchaw.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String provider;

    private String refreshToken;

    private String password;

    private String web_email;

    private String school;

    private String imageUrl;

    private String content;

    private String facebookUrl;

    private String instagramUrl;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserCountry> country = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserLanguage> language=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserHopeLanguage> hopeLanguage=new ArrayList<>();

    @OneToMany(mappedBy = "toUser")
    private List<Follow>toFollows=new ArrayList<>();



//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Follow>follows=new ArrayList<>();

    private LocalDateTime regDate;

    private Long views=0L;

    @Enumerated(EnumType.STRING)
    private ROLE role;

    public static User createUser(String email,String name,String provider,String password,
                                  String web_email,String school,String imageUrl,String content,
                                  List<UserCountry>country,List<UserLanguage> language,List<UserHopeLanguage> hopeLanguage,
                                  String facebookUrl,String instagramUrl){


        User user=new User();
        user.content=content;
        for(int i=0;i<country.size();i++){
            country.get(i).addUser(user);
        }
        user.email=email;
        for(int i=0;i<language.size();i++){
            language.get(i).addUser(user);
        }
        user.imageUrl=imageUrl;
        user.name=name;
        for(int i=0;i<hopeLanguage.size();i++){
            hopeLanguage.get(i).addUser(user);
        }
        user.regDate=LocalDateTime.now();
        user.password=password;
        user.role=ROLE.USER;
        user.provider=provider;
        user.web_email=web_email;
        user.school=school;
        user.facebookUrl=facebookUrl;
        user.instagramUrl =instagramUrl;

        return user;

    }



    public void changeRefreshToken(String refreshToken) {

        this.refreshToken=refreshToken;
    }
    public void addView(){
        this.views+=1;
    }

    public void changeContent(String content){
        this.content=content;
    }
    public void changeFaceBookUrl(String url){
        this.facebookUrl=url;
    }
    public void changeInstagramUrl(String url){
        this.instagramUrl=url;
    }
    public void changeImageUrl(String url){
        this.imageUrl=url;
    }
}
