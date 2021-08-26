package com.project.chawchaw.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
    private String provider;
    private String kakaoToken;
    private String facebookToken;
    private String facebookId;
}
