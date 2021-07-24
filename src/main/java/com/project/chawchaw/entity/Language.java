package com.project.chawchaw.entity;

import com.nimbusds.langtag.LangTagException;
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

    public static Language createLanguage(String name){
        Language language=new Language();
        language.name=name ;
    return language;

    }

}
