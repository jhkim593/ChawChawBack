package com.project.chawchaw.repository;

import com.project.chawchaw.entity.UserCountry;
import com.project.chawchaw.entity.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserLanguageRepository extends JpaRepository<UserLanguage,Long> {

//    @Query("select l.name from UserLanguage ul join fetch ul.language l where ul.user.id=:userId")
//    List<String> findUserLanguage(@Param("userId")Long userId);

    @Query("select ul from UserLanguage ul where ul.user.id=:userId and ul.language.name=:name")
    Optional<UserLanguage> findUserLanguageByUserIdAndCountry(@Param("userId")Long userId, @Param("name")String name);

    @Modifying
    @Query("delete from UserLanguage ul where ul.user.id=:userId")
    int deleteByUserId(@Param("userId")Long userId);
}
