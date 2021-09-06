package com.project.chawchaw.repository.follow;

import com.project.chawchaw.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    @Query(value = "select f from Follow f where f.fromUser.id =:fromUserId and f.toUser.id=:toUserId ")
    Optional<Follow>findByFollow(@Param("fromUserId")Long fromUserId,@Param("toUserId")Long toUserId);

    @Query("select count(f) from Follow f where f.toUser.id=:toUserId")
    int countFollow(@Param("toUserId")Long toUserId);

    @Modifying
    @Query("delete from Follow f where f.toUser.id=:userId or f.fromUser.id=:userId")
    int deleteFollowByUserId(@Param("userId")Long userId);
}
