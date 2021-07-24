package com.project.chawchaw.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userFrom_id")
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userTo_id")
    private User toUser;

    private LocalDateTime regDate;

    public static Follow createFollow(User fromUser,User toUser){
        Follow follow=new Follow();
        follow.fromUser=fromUser;
        toUser.getToFollows().add(follow);
        follow.toUser=toUser;

        follow.regDate=LocalDateTime.now();
        return follow;

    }



}
