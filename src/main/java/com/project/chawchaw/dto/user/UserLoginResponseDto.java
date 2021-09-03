package com.project.chawchaw.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginResponseDto {

    private UserProfileDto profile;
    private UserTokenResponseDto token;
}
