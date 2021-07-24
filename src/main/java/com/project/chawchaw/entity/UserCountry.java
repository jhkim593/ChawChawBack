package com.project.chawchaw.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public static UserCountry createUserCountry(Country country) {
        UserCountry userCountry=new UserCountry();
        userCountry.country=country;
        return userCountry;
    }
    public void addUser(User user){
        this.user=user;
        user.getCountry().add(this);
    }
}
