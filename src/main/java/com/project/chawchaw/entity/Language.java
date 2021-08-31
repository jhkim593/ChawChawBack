package com.project.chawchaw.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Entity

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String abbr;

    public static Language createLanguage(String name,String abbr){
        Language language=new Language();
        language.name=name ;
        language.abbr=abbr;
    return language;

    }

}
