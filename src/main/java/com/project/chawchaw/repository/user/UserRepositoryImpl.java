package com.project.chawchaw.repository.user;

import com.project.chawchaw.dto.SortOrders;
import com.project.chawchaw.dto.UserSearch;
import com.project.chawchaw.dto.UsersDto;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.repository.UserLanguageRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.persistence.EntityManager;

import java.util.List;

import static com.project.chawchaw.entity.QCountry.*;
import static com.project.chawchaw.entity.QFollow.*;
import static com.project.chawchaw.entity.QLanguage.*;
import static com.project.chawchaw.entity.QUser.user;
import static com.project.chawchaw.entity.QUserCountry.userCountry;
import static com.project.chawchaw.entity.QUserHopeLanguage.*;
import static com.project.chawchaw.entity.QUserLanguage.*;
import static org.springframework.util.StringUtils.hasText;


public class UserRepositoryImpl implements  UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Autowired
    UserLanguageRepository userLanguageRepository;

    @Autowired
    private EntityManager em;

    public UserRepositoryImpl(EntityManager em){this.queryFactory=new JPAQueryFactory(em);}


    @Override
    public Slice<UsersDto> users(Long lastUserId, Pageable pageable, UserSearch userSearch,String school) {
        List<UsersDto> usersDtos = queryFactory.select(Projections.constructor(UsersDto.class, user.id, user.imageUrl, user.content,
                                user.regDate, user.views, user.country.size())).from(userCountry, userLanguage)
                                .join(userCountry.country, country)

                                .join(userLanguage.user, user)
                                .join(userCountry.user, user).where(
                                        countryEq(userSearch.getCountry())
                                        , languageEq(userSearch.getLanguage())
                        , nameEq(userSearch.getName())
                        , user.school.eq(school)).fetch();


        return null;





    }
    QUser user2=new QUser("user2");

    @Override
    public List<UsersDto> usersList(UserSearch userSearch, String school) {
        List<UsersDto> usersList = queryFactory.select(Projections.constructor(UsersDto.class, user.id, user.imageUrl, user.content,
                user.regDate, user.views,user.toFollows.size())).distinct().from( userLanguage,userCountry)

                .join(userLanguage.language,language)
                .join(userLanguage.user,user)
                .join(userCountry.user,user2)
                .join(userCountry.country,country)

                .where(
                        countryEq(userSearch.getCountry())
                        , languageEq(userSearch.getLanguage())
                        , nameEq(userSearch.getName())
                        , user.school.eq(school)
                        ,user.id.eq(user2.id))
                .fetch();
        return usersList;
    }

    private BooleanExpression countryEq(String coun) {
        return hasText(coun) ? country.name.eq(coun) : null;
    }


    private BooleanExpression nameEq(String name) {

        return hasText(name) ? user.name.eq(name) : null;
    }

//
//    private BooleanExpression hopeLanguageEq(String hopeLanguage) {
//        return  hasText(hopeLanguage)?user.hopeLanguage.eq(hopeLanguage):null;
//    }
    private BooleanExpression languageEq(String lang) {

        return hasText(lang) ? language.name.eq(lang) : null;
    }
//
//    private BooleanExpression countryEq(String country) {
//        return  hasText(country)?user.country.eq(country):null;
//    }
}
