package com.project.chawchaw.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String abbr;

    public static Language createLanguage(String name,String abbr){
        Language language=new Language();
        language.name=name ;
        language.abbr=abbr;
    return language;

    }

}
