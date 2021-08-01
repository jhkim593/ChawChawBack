package com.project.chawchaw.dto.social;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialLoginRequestDto {
    private String accessToken;
    private String email;
    private String code;
}
