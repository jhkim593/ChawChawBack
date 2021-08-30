package com.project.chawchaw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequestDto {
    private String verificationNumber;
    private String email;
}
