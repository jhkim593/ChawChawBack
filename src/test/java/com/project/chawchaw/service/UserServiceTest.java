package com.project.chawchaw.service;

import com.project.chawchaw.dto.user.UserDto;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.CountryRepository;
import com.project.chawchaw.repository.LanguageRepository;
import com.project.chawchaw.repository.user.UserRepository;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(false)

class UserServiceTest {


    @Autowired
    EntityManager em;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    LanguageRepository languageRepository;

    @BeforeEach
    public void beforeEach()throws Exception{
//        Language language1=Language.createLanguage("한국어");
//        Country country1=Country.createCountry("한국");
//        em.persist(language1);
//        em.persist(country1);
//        Language language2=Language.createLanguage("일본어");
//        Country country2=Country.createCountry("일본");
//        em.persist(language2);
//        em.persist(country2);
//        Language language3=Language.createLanguage("영어");
//        Country country3=Country.createCountry("미국");
//        em.persist(country3);
//        em.persist(language3);
//        Language language4=Language.createLanguage("불어");
//        Country country4=Country.createCountry("프랑스");
//        em.persist(country4);
//        em.persist(language4);
//
//        List<UserCountry>user1c=new ArrayList<>();
//        List<UserLanguage>user1l=new ArrayList<>();
//        List<UserHopeLanguage>user1h=new ArrayList<>();
//
//        user1c.add(UserCountry.createUserCountry(country1));
//        user1c.add(UserCountry.createUserCountry(country2));
//        user1l.add(UserLanguage.createUserLanguage(language1));
//        user1l.add(UserLanguage.createUserLanguage(language2));
//
//        user1h.add(UserHopeLanguage.createUserHopeLanguage(language1));
//
//
//
//
//
//        em.persist(User.createUser("1",null,null,null,null,"단국대",null,"ggggggg",user1c,user1l,user1h,null,null));
//
//
//        List<UserCountry> user2c=new ArrayList<>();
//        List<UserLanguage>user2l=new ArrayList<>();
//        List<UserHopeLanguage>user2h=new ArrayList<>();
//        user2h.add(UserHopeLanguage.createUserHopeLanguage(language1));
//        user2c.add(UserCountry.createUserCountry(country3));
//        user2l.add(UserLanguage.createUserLanguage(language3));
//        user2c.add(UserCountry.createUserCountry(country4));
//        user2l.add(UserLanguage.createUserLanguage(language4));
//        em.persist(User.createUser("2",null,null,null,null,"단국대",null,"ggggggg",user2c,user2l,user2h,null,null));
//
//
//        List<UserCountry>user3c=new ArrayList<>();
//        List<UserLanguage>user3l=new ArrayList<>();
//        List<UserHopeLanguage>user3h=new ArrayList<>();
//        user3h.add(UserHopeLanguage.createUserHopeLanguage(language1));
//        user3c.add(UserCountry.createUserCountry(country1));
//        user3l.add(UserLanguage.createUserLanguage(language1));
//        user3c.add(UserCountry.createUserCountry(country3));
//        user3l.add(UserLanguage.createUserLanguage(language3));
//        em.persist(User.createUser("3",null,null,null,null,"단국대",null,"ggggggg",user3c,user3l,user3h,null,null));





    }

    @Test
    public void detail()throws Exception{
       //given

        Language language1=Language.createLanguage("한국어","kor");
        Country country1=Country.createCountry("한국");
       countryRepository.save(country1);
       languageRepository.save(language1);


       //when
//        User user = userRepository.findByEmail("3").orElseThrow(UserNotFoundException::new);
//        UserDto userDto = userService.detailUser(user.getId());
//
//        //then
//        assertThat(userDto.getContent()).isEqualTo(user.getContent());
////        assertThat(userDto.getCountry()).isEqualTo(user.getCountry());
//        assertThat(userDto.getFacebookUrl()).isEqualTo(user.getFacebookUrl());
//        assertThat(userDto.getInstagramUrl()).isEqualTo(user.getInstagramUrl());
//        assertThat(userDto.getName()).isEqualTo(user.getName());
//        assertThat(userDto.getFollows()).isEqualTo(user.getToFollows().size());


    }
    @Test
    public void jasyptTest()throws Exception{
        String password = "";
        String url="";
        String username="";
        String base="";
        String secret="";
        String path="";
        String client_id="";
        String redirect="";
        String id="";
        String pw="";


        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(System.getProperty("jasypt.encryptor.password"));
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encryptedTextid = jasypt.encrypt(id);
        System.out.println("======================");
        System.out.println(encryptedTextid);
        String decryptedTextid = jasypt.decrypt(encryptedTextid);
        assertThat(id).isEqualTo(decryptedTextid);

        String encryptedTextpw = jasypt.encrypt(pw);
        System.out.println("======================route");
        System.out.println(encryptedTextpw);
        String decryptedTextpw = jasypt.decrypt(encryptedTextpw);
        assertThat(pw).isEqualTo(decryptedTextpw);


        String encryptedTextpa = jasypt.encrypt(password);
        System.out.println("======================password");
        System.out.println(encryptedTextpa);
        String decryptedTextpa = jasypt.decrypt(encryptedTextpa);
        assertThat(password).isEqualTo(decryptedTextpa);

        String encryptedTextpaurl = jasypt.encrypt(url);
        System.out.println("======================url");
        System.out.println(encryptedTextpaurl);
        String decryptedTexturl = jasypt.decrypt(encryptedTextpaurl);
        assertThat(url).isEqualTo(decryptedTexturl);

        String encryptedTextpausername = jasypt.encrypt(username);
        System.out.println("======================username");
        System.out.println(encryptedTextpausername);
        String decryptedTextusername = jasypt.decrypt(encryptedTextpausername);
        assertThat(username).isEqualTo(decryptedTextusername);

        String encryptedTextpabase = jasypt.encrypt(base);
        System.out.println("======================base");
        System.out.println(encryptedTextpabase);
        String decryptedTextbase= jasypt.decrypt(encryptedTextpabase);
        assertThat(base).isEqualTo(decryptedTextbase);

        String encryptedTextpath = jasypt.encrypt(path);
        System.out.println("======================path");
        System.out.println(encryptedTextpath);
        String decryptedTextpath = jasypt.decrypt(encryptedTextpath);
        assertThat(path).isEqualTo(decryptedTextpath);

        String encryptedTextclient = jasypt.encrypt(client_id);
        System.out.println("======================client_id");
        System.out.println(encryptedTextclient);
        String decryptedTextclient = jasypt.decrypt(encryptedTextclient);
        assertThat(client_id).isEqualTo(decryptedTextclient);

        String encryptedTextredirect = jasypt.encrypt(redirect);
        System.out.println("======================admin");
        System.out.println(encryptedTextredirect);
        String decryptedTextredirect = jasypt.decrypt(encryptedTextredirect);
        assertThat(redirect).isEqualTo(decryptedTextredirect);

        String encryptedTextsecret = jasypt.encrypt(secret);
        System.out.println("======================secret");
        System.out.println(encryptedTextsecret);
        String decryptedTextsecret = jasypt.decrypt(encryptedTextsecret);
        assertThat(secret).isEqualTo(decryptedTextsecret);





    }

}