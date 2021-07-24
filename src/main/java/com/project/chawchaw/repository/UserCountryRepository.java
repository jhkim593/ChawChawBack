package com.project.chawchaw.repository;

import com.project.chawchaw.entity.UserCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCountryRepository extends JpaRepository<UserCountry,Long> {

//    @Query("select c.name from UserCountry uc join fetch uc.country c where uc.user.id=:userId")
//    List<String>findUserCountry(@Param("userId")Long userId);
    @Query("select us from UserCountry us where us.user.id=:userId and us.country.name=:name")
    Optional<UserCountry>findUserCountryByUserIdAndCountry(@Param("userId")Long userId,@Param("name")String name);

    @Modifying
    @Query("delete from UserCountry uc where uc.user.id=:userId")
    int deleteByUserId(@Param("userId")Long userId);
}
