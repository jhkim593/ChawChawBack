package com.project.chawchaw.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearch {
    private String name;
    private String country;
    private String language;
    private String hopeLanguage;
    private SortOrders order;
}
