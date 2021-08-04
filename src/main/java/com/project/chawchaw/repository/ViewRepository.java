package com.project.chawchaw.repository;

import com.project.chawchaw.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ViewRepository extends JpaRepository<View,Long> {

    @Modifying
    @Query(value = "truncate table views", nativeQuery = true)
    void truncateTable();


    @Query("select v from View v where v.fromUser.id=:fromUserId and v.toUser.id=:toUserId")
    Optional<View>findViewByUserId(@Param("fromUserId")Long fromUserId,@Param("toUserId")Long toUserId);
}
