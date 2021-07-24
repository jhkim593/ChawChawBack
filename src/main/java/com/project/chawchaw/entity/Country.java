package com.project.chawchaw.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@AllArgsConstructor
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static Country createCountry(String name){
        Country country=new Country();
        country.name=name ;
        return country;

}
}
