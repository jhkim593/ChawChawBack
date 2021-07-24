package com.project.chawchaw.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
}
