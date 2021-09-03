package com.project.chawchaw.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTokenDto {
    private Long id;
    private String accessToken;
    private String refreshToken;

}
