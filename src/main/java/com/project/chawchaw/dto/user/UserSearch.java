package com.project.chawchaw.dto.user;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserSearch {
    private String name;
    private String hopeLanguage;
    private String language;
    private String order;
    private int pageNo;
//    private Long userId;
    private String school;
    List<Long> excludes=new ArrayList<>();
    private Long lastUserId;
}
