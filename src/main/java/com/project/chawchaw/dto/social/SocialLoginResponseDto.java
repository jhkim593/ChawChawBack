package com.project.chawchaw.dto.social;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SocialLoginResponseDto {

    private String email;
    private String name;
    private String imageUrl;
}
