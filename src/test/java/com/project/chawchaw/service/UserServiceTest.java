package com.project.chawchaw.service;

import com.project.chawchaw.dto.user.UserDto;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class UserServiceTest {


    @Autowired
    EntityManager em;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @BeforeEach

    public void beforeEach()throws Exception{
        Language language1=Language.createLanguage("한국어");
        Country country1=Country.createCountry("한국");
        em.persist(language1);
        em.persist(country1);
        Language language2=Language.createLanguage("일본어");
        Country country2=Country.createCountry("일본");
        em.persist(language2);
        em.persist(country2);
        Language language3=Language.createLanguage("영어");
        Country country3=Country.createCountry("미국");
        em.persist(country3);
        em.persist(language3);
        Language language4=Language.createLanguage("불어");
        Country country4=Country.createCountry("프랑스");
        em.persist(country4);
        em.persist(language4);

        List<UserCountry>user1c=new ArrayList<>();
        List<UserLanguage>user1l=new ArrayList<>();
        List<UserHopeLanguage>user1h=new ArrayList<>();

        user1c.add(UserCountry.createUserCountry(country1));
        user1c.add(UserCountry.createUserCountry(country2));
        user1l.add(UserLanguage.createUserLanguage(language1));
        user1l.add(UserLanguage.createUserLanguage(language2));

        user1h.add(UserHopeLanguage.createUserHopeLanguage(language1));





        em.persist(User.createUser("1",null,null,null,null,"단국대",null,"ggggggg",user1c,user1l,user1h,null,null));


        List<UserCountry> user2c=new ArrayList<>();
        List<UserLanguage>user2l=new ArrayList<>();
        List<UserHopeLanguage>user2h=new ArrayList<>();
        user2h.add(UserHopeLanguage.createUserHopeLanguage(language1));
        user2c.add(UserCountry.createUserCountry(country3));
        user2l.add(UserLanguage.createUserLanguage(language3));
        user2c.add(UserCountry.createUserCountry(country4));
        user2l.add(UserLanguage.createUserLanguage(language4));
        em.persist(User.createUser("2",null,null,null,null,"단국대",null,"ggggggg",user2c,user2l,user2h,null,null));


        List<UserCountry>user3c=new ArrayList<>();
        List<UserLanguage>user3l=new ArrayList<>();
        List<UserHopeLanguage>user3h=new ArrayList<>();
        user3h.add(UserHopeLanguage.createUserHopeLanguage(language1));
        user3c.add(UserCountry.createUserCountry(country1));
        user3l.add(UserLanguage.createUserLanguage(language1));
        user3c.add(UserCountry.createUserCountry(country3));
        user3l.add(UserLanguage.createUserLanguage(language3));
        em.persist(User.createUser("3",null,null,null,null,"단국대",null,"ggggggg",user3c,user3l,user3h,null,null));





    }

    @Test
    public void detail()throws Exception{
       //given

       //when
        User user = userRepository.findByEmail("3").orElseThrow(UserNotFoundException::new);
        UserDto userDto = userService.detailUser(user.getId());

        //then
        assertThat(userDto.getContent()).isEqualTo(user.getContent());
//        assertThat(userDto.getCountry()).isEqualTo(user.getCountry());
        assertThat(userDto.getFacebookUrl()).isEqualTo(user.getFacebookUrl());
        assertThat(userDto.getInstagramUrl()).isEqualTo(user.getInstagramUrl());
        assertThat(userDto.getName()).isEqualTo(user.getName());
        assertThat(userDto.getFollows()).isEqualTo(user.getToFollows().size());


    }

}