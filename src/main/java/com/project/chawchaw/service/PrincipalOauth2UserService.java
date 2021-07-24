package com.project.chawchaw.service;

import com.project.chawchaw.config.provider.FaceBookUserInfo;
import com.project.chawchaw.config.provider.OAuth2UserInfo;
import com.project.chawchaw.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    // userRequest 는 code를 받아서 accessToken을 응답 받은 객체
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회
        System.out.println("+============================");
        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        System.out.println("oAuth2User : " + oAuth2User);

//        return processOAuth2User(userRequest, oAuth2User);
        return super.loadUser(userRequest);
    }

//    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
//
//        // Attribute를 파싱해서 공통 객체로 묶는다. 관리가 편함.
//        OAuth2UserInfo oAuth2UserInfo = null;
//
//        if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
//            System.out.println("facebook login");
//            oAuth2UserInfo = new FaceBookUserInfo(oAuth2User.getAttributes());
//        }
//
//        else {
//            System.out.println("only facebook");
//        }
//
//
//        Optional<User> userOptional =
//                userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
//
//        User user;
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//            // user가 존재하면 update 해주기
//            user.setEmail(oAuth2UserInfo.getEmail());
//            userRepository.save(user);
//        } else {
//            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
//            user = User.builder()
//                    .username(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
//                    .email(oAuth2UserInfo.getEmail())
//                    .role("ROLE_USER")
//                    .provider(oAuth2UserInfo.getProvider())
//                    .providerId(oAuth2UserInfo.getProviderId())
//                    .build();
//            userRepository.save(user);
//        }
//
//        return new PrincipalDetails(user, oAuth2User.getAttributes());
//    }
}