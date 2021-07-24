package com.project.chawchaw.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@ToString
public class KakaoProfile {
    public String id;
    public KakaoAccount kakao_account;
    public String connected_at;


    @Getter
    @Setter
    @ToString
    public static class KakaoAccount {

        public Boolean has_email;
        public Boolean email_needs_agreement;
        public Boolean is_email_valid;
        public Boolean is_email_verified;
        public String email;

    }



}