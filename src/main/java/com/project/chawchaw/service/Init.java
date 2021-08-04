package com.project.chawchaw.service;

import com.project.chawchaw.entity.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Init {
    private final InitService initService;


    @PostConstruct
    public void init(){
        initService.Init();

    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService{
        private final EntityManager em;
        private final PasswordEncoder passwordEncoder;
        private final UserService userService;

        public void Init(){

             //대표언어 공통 중국 중국어
            //user1 한국 한국어  일본 일본어
            //user2 미국 영어    프랑스 불어
            //user3 한국 한국어 미국 영어
            Language rlanguage=Language.createLanguage("중국어","chi");
            Country rcountry=Country.createCountry("중국");
            em.persist(rlanguage);
            em.persist(rcountry);

            UserLanguage ruserLanguage = UserLanguage.createUserLanguage(rlanguage);
            ruserLanguage.changeRep();
            UserHopeLanguage ruserHopeLanguage = UserHopeLanguage.createUserHopeLanguage(rlanguage);
            ruserHopeLanguage.changeRep();
            UserCountry ruserCountry = UserCountry.createUserCountry(rcountry);
            ruserCountry.changeRep();


            Language language1=Language.createLanguage("한국어","kor");
            Country country1=Country.createCountry("한국");
            em.persist(language1);
            em.persist(country1);
            Language language2=Language.createLanguage("일본어","jap");
            Country country2=Country.createCountry("일본");
            em.persist(language2);
            em.persist(country2);
            Language language3=Language.createLanguage("영어","eng");
            Country country3=Country.createCountry("미국");
            em.persist(country3);
            em.persist(language3);
            Language language4=Language.createLanguage("불어","fr");
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


            User user1 = User.createUser("1", null, null, passwordEncoder.encode("1"),
                    null, "단국대", null
//                    ,"ggggggg", user1c, user1l, user1h,
//                    null, null, ruserCountry, ruserLanguage, ruserHopeLanguage
            );

            em.persist(user1);



            List<UserCountry>user2c=new ArrayList<>();
            List<UserLanguage>user2l=new ArrayList<>();
            List<UserHopeLanguage>user2h=new ArrayList<>();
            user2h.add(UserHopeLanguage.createUserHopeLanguage(language1));
            user2c.add(UserCountry.createUserCountry(country3));
            user2l.add(UserLanguage.createUserLanguage(language3));
            user2c.add(UserCountry.createUserCountry(country4));
            user2l.add(UserLanguage.createUserLanguage(language4));
            User user2 = User.createUser("2", null, null, passwordEncoder.encode("2"), null,
                    "단국대", null
//                    , "ggggggg", user2c, user2l, user2h,
//                    null, null, ruserCountry, ruserLanguage, ruserHopeLanguage
            );
            em.persist(user2);


            List<UserCountry>user3c=new ArrayList<>();
            List<UserLanguage>user3l=new ArrayList<>();
            List<UserHopeLanguage>user3h=new ArrayList<>();
            user3h.add(UserHopeLanguage.createUserHopeLanguage(language1));
            user3c.add(UserCountry.createUserCountry(country1));
            user3l.add(UserLanguage.createUserLanguage(language1));
            user3c.add(UserCountry.createUserCountry(country3));
            user3l.add(UserLanguage.createUserLanguage(language3));
            User user3 = User.createUser("3", null, null, passwordEncoder.encode("3"),
                    null, "단국대",
                    null
//                    ,"ggggggg", user3c, user3l, user3h, null, null,
//                    ruserCountry, ruserLanguage, ruserHopeLanguage
            );
            em.persist(user3);


            View view1 = View.createView(user1, user2);
            View view2 = View.createView(user1, user3);
            em.persist(view1);
            em.persist(view2);

        }
        }


}
