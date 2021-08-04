package com.project.chawchaw.service;



import com.project.chawchaw.config.JwtTokenProvider;
import com.project.chawchaw.dto.user.UserLoginRequestDto;
import com.project.chawchaw.dto.user.UserSignUpByProviderRequestDto;
import com.project.chawchaw.dto.user.UserSignUpRequestDto;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.exception.*;
import com.project.chawchaw.repository.CountryRepository;
import com.project.chawchaw.repository.FollowRepository;
import com.project.chawchaw.repository.LanguageRepository;
import com.project.chawchaw.repository.user.UserRepository;
import com.project.chawchaw.dto.user.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor


public class SignService {


    private final JavaMailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KakaoService kakaoService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate redisTemplate;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final FollowRepository followRepository;



    private MimeMessage createMessage(String to,String code)throws Exception{
        logger.info("보내는 대상 : "+ to);
//        logger.info("인증 번호 : " + ePw);
        MimeMessage  message = emailSender.createMimeMessage();


        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject("chawchaw 확인 코드: " + code); //제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 가입 창이 있는 브라우저 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";
        msg += "<a href=\"\" style=\"text-decoration: none; color: #434245;\" rel=\"noreferrer noopener\" target=\"_blank\">chawchaw</a>";

        message.setText(msg, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("fpdlwjzlr@gmail.com","chawchaw")); //보내는 사람

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    public String sendSimpleMessage(String to)throws Exception {
        String code=createKey();
        MimeMessage message = createMessage(to,code);


        try{
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return code;
    }


    @Transactional
    public void signup(UserSignUpRequestDto requestDto){



//        UUID uuid = UUID.randomUUID();
//        String uuidFilename = uuid + "_" + file.getOriginalFilename();
//        System.out.println("-=--------------------=======================");
//        Path filePath = Paths.get(fileRealPath + uuidFilename);
//        try {
//            Files.write(filePath, file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Country repCountry = countryRepository.findByName(requestDto.getRepCountry()).orElseThrow(CountryNotFoundException::new);
//        Language repLanguage = languageRepository.findByAbbr(requestDto.getRepLanguage()).orElseThrow(LanguageNotFoundException::new);
//        Language repHopeLanguage = languageRepository.findByAbbr(requestDto.getRepHopeLanguage()).orElseThrow(LanguageNotFoundException::new);
//
//        UserHopeLanguage userRepHopeLanguage = UserHopeLanguage.createUserHopeLanguage(repHopeLanguage);
//        userRepHopeLanguage.changeRep();
//        UserLanguage userRepLanguage = UserLanguage.createUserLanguage(repLanguage);
//        userRepLanguage.changeRep();
//        UserCountry userRepCountry = UserCountry.createUserCountry(repCountry);
//        userRepCountry.changeRep();
//
//
//
//
//        List<UserHopeLanguage> hopeLanguageList=new ArrayList<>();
//        for(int i=0;i<requestDto.getHopeLanguage().size();i++){
//            String hopeLanguageName=requestDto.getHopeLanguage().get(i);
//            Language language = languageRepository.findByAbbr(hopeLanguageName).orElseThrow(LanguageNotFoundException::new);
//            UserHopeLanguage userHopeLanguage = UserHopeLanguage.createUserHopeLanguage(language);
//            hopeLanguageList.add(userHopeLanguage);
//
//        }
//       List<UserLanguage>languageList=new ArrayList<>();
//        for(int i=0;i<requestDto.getLanguage().size();i++){
//            String LanguageName=requestDto.getLanguage().get(i);
//            Language language = languageRepository.findByAbbr(LanguageName).orElseThrow(LanguageNotFoundException::new);
//            UserLanguage userLanguage = UserLanguage.createUserLanguage(language);
//            languageList.add(userLanguage);
//
//        }
//
//        List<UserCountry>countryList=new ArrayList<>();
//        for(int i=0;i<requestDto.getCountry().size();i++){
//            String countryName=requestDto.getCountry().get(i);
//            Country country = countryRepository.findByName(countryName).orElseThrow(CountryNotFoundException::new);
//            UserCountry userCountry = UserCountry.createUserCountry(country);
//            countryList.add(userCountry);
//
//        }



//        validUser(requestDto.getEmail());


        if(validUserWithProvider(requestDto.getEmail(),requestDto.getProvider())){

            throw new UserAlreadyExistException();

        }

        userRepository.save(User.createUser(requestDto.getEmail(),requestDto.getName(),requestDto.getProvider(),passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getWeb_email(),requestDto.getSchool(),requestDto.getImageUrl()
//                ,requestDto.getContent(), countryList, languageList,hopeLanguageList,requestDto.getFacebookUrl(),requestDto.getInstagramUrl(),userRepCountry,userRepLanguage,userRepHopeLanguage
        ));

    }




    @Transactional
    public UserLoginResponseDto login(UserLoginRequestDto requestDto){

        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(LoginFailureException::new);
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new LoginFailureException();
        }
        user.changeRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDto(user.getId(), jwtTokenProvider.createToken(String.valueOf(user.getId())), user.getRefreshToken());
    }


    @Transactional
    public UserLoginResponseDto loginByProvider(String email, String provider) {


        User user = userRepository.findUserByEmailAndProvider(email, provider).orElseThrow(UserNotFoundException::new);

//
        user.changeRefreshToken(jwtTokenProvider.createRefreshToken());
        return new UserLoginResponseDto(user.getId(), jwtTokenProvider.createToken(String.valueOf(user.getId())), user.getRefreshToken() );
//

    }

//    private String getEmailByProvider(String accessToken, String provider) {
//        if(provider.equals("kakao")) {
//            return kakaoService.getKakaoProfile(accessToken).getId();
//        }
//        throw new InvalidateProviderException();
//    }

//    @Transactional
//    public void signUpByProvider(UserSignUpByProviderRequestDto requestDto,String provider) {
//
//        if(validUserWithProvider(requestDto.getEmail(),provider)){
//            throw new UserAlreadyExistException();
//        }
//
//        Country repCountry = countryRepository.findByName(requestDto.getRepCountry()).orElseThrow(CountryNotFoundException::new);
//        Language repLanguage = languageRepository.findByAbbr(requestDto.getRepLanguage()).orElseThrow(LanguageNotFoundException::new);
//        Language repHopeLanguage = languageRepository.findByAbbr(requestDto.getRepHopeLanguage()).orElseThrow(LanguageNotFoundException::new);
//
//        UserHopeLanguage userRepHopeLanguage = UserHopeLanguage.createUserHopeLanguage(repHopeLanguage);
//        userRepHopeLanguage.changeRep();
//        UserLanguage userRepLanguage = UserLanguage.createUserLanguage(repLanguage);
//        userRepLanguage.changeRep();
//        UserCountry userRepCountry = UserCountry.createUserCountry(repCountry);
//        userRepCountry.changeRep();
//
//        List<UserHopeLanguage> hopeLanguageList=new ArrayList<>();
//        for(int i=0;i<requestDto.getHopeLanguage().size();i++){
//            String hopeLanguageName=requestDto.getHopeLanguage().get(i);
//            Language language = languageRepository.findByAbbr(hopeLanguageName).orElseThrow(LanguageNotFoundException::new);
//            UserHopeLanguage userHopeLanguage = UserHopeLanguage.createUserHopeLanguage(language);
//            hopeLanguageList.add(userHopeLanguage);
//
//        }
//        List<UserLanguage>languageList=new ArrayList<>();
//        for(int i=0;i<requestDto.getLanguage().size();i++){
//            String LanguageName=requestDto.getLanguage().get(i);
//            Language language = languageRepository.findByAbbr(LanguageName).orElseThrow(LanguageNotFoundException::new);
//            UserLanguage userLanguage = UserLanguage.createUserLanguage(language);
//            languageList.add(userLanguage);
//
//        }
//
//        List<UserCountry>countryList=new ArrayList<>();
//        for(int i=0;i<requestDto.getCountry().size();i++){
//            String countryName=requestDto.getCountry().get(i);
//            Country country = countryRepository.findByName(countryName).orElseThrow(CountryNotFoundException::new);
//            UserCountry userCountry = UserCountry.createUserCountry(country);
//            countryList.add(userCountry);
//
//        }
//
//
//
//        userRepository.save(User.createUser(requestDto.getEmail(),requestDto.getName(),provider,null,
//                requestDto.getWeb_email(),requestDto.getSchool(),requestDto.getImageUrl(),requestDto.getContent(),countryList,languageList,
//                hopeLanguageList,requestDto.getFacebookUrl(),requestDto.getInstagramUrl(),userRepCountry,userRepLanguage,userRepHopeLanguage));
//
//    }


    public Boolean validUserWithProvider(String email, String provider) {
        if(userRepository.findUserByEmailAndProvider(email, provider).isPresent())

        {
            return true;
        }
        return false;
    }

    private void validUser(String email) {
        if(userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistException();
    }

    public boolean userCheck(String email){
        if(userRepository.findByEmail(email).isPresent())
           return true;
        else return false;
    }
//    public String getImageUrl(String token){
//        Long id=Long.valueOf(jwtTokenProvider.getUserPk(token));
//        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//        return user.getImageUrl();
//    }



        @Transactional
        public void logoutMember(String token) {
            redisTemplate.opsForValue().set( "token:" + token, "v", jwtTokenProvider.getRemainingSeconds(token));
            User user = userRepository.findById(Long.valueOf(jwtTokenProvider.getUserPk(token))).orElseThrow(UserNotFoundException::new);
            user.changeRefreshToken("invalidate");

    }



    public void userDelete(String token) {
        User user = userRepository.findById(Long.valueOf(jwtTokenProvider.getUserPk(token))).orElseThrow(UserNotFoundException::new);
        followRepository.deleteFollowByUserId(user.getId());
        userRepository.delete(user);

    }


}
