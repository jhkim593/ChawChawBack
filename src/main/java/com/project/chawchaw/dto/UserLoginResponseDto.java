package com.project.chawchaw.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginResponseDto {
    private Long id;
    private String token;
    private String refreshToken;
}
