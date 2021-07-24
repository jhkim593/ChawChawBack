package com.project.chawchaw.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserHopeLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hopeLanguage_id")
    private Language hopeLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public static UserHopeLanguage createUserHopeLanguage(Language language){
        UserHopeLanguage userHopeLanguage=new UserHopeLanguage();
        userHopeLanguage.hopeLanguage=language;
        return userHopeLanguage;

    }

    public void addUser(User user) {
        this.user=user;
        user.getHopeLanguage().add(this);
    }
}