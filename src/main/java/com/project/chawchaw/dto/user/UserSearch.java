package com.project.chawchaw.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearch {
    private String name;
    private String hopeLanguage;
    private String language;
    private String order;
    private int pageNo;
}
