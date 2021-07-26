package com.project.chawchaw.service;

import com.project.chawchaw.dto.user.*;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.exception.CountryNotFoundException;
import com.project.chawchaw.exception.LanguageNotFoundException;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.*;
import com.project.chawchaw.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;
    private final UserLanguageRepository userLanguageRepository;
    private final UserHopeLanguageRepository userHopeLanguageRepository;
    private final UserCountryRepository userCountryRepository;


    @Transactional
    public UserDto detailUser(Long userId){

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addView();
        int followCount = followRepository.countFollow(userId);

       UserDto userDto= new UserDto(user,followCount);

       return userDto;





    }
    public List<UsersDto> users(UserSearch userSearch, String school){
        return userRepository.usersList(userSearch,school);
    }


    public UserProfileDto userProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return new UserProfileDto(user);

    }

    @Transactional
    public void userProfileUpdate(UserUpdateDto updateDto, Long id) {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (!updateDto.getCountry().isEmpty()) {
            userCountryRepository.deleteByUserId(id);
            for (int i = 0; i < updateDto.getCountry().size(); i++) {
                Country country = countryRepository.findByName(updateDto.getCountry().get(i)).orElseThrow(CountryNotFoundException::new);
                UserCountry userCountry = UserCountry.createUserCountry(country);
                userCountry.addUser(user);

            }

        }
        if (!updateDto.getLanguage().isEmpty()) {
            userLanguageRepository.deleteByUserId(id);
            for (int i = 0; i < updateDto.getLanguage().size(); i++) {
                Language language = languageRepository.findByName(updateDto.getLanguage().get(i)).orElseThrow(LanguageNotFoundException::new);
                UserLanguage userLanguage = UserLanguage.createUserLanguage(language);
                userLanguage.addUser(user);

            }
        }

        if (!updateDto.getHopeLanguage().isEmpty()) {
            userHopeLanguageRepository.deleteByUserId(id);
            for (int i = 0; i < updateDto.getHopeLanguage().size(); i++) {
                Language language = languageRepository.findByName(updateDto.getHopeLanguage().get(i)).orElseThrow(LanguageNotFoundException::new);
                UserHopeLanguage userHopeLanguage = UserHopeLanguage.createUserHopeLanguage(language);
                userHopeLanguage.addUser(user);

            }
        }
        user.changeImageUrl(updateDto.getImageUrl());
        user.changeInstagramUrl(updateDto.getInstagramUrl());
        user.changeFaceBookUrl(updateDto.getFacebookUrl());
        user.changeContent(updateDto.getContent());

    }
//



//
//    @Transactional
//
//    public void userProfileDelete(String content,UserUpdateDto updateDto,Long id){
//
//        if(content.equals("country")){
//            UserCountry userCountry = userCountryRepository.findUserCountryByUserIdAndCountry(id, updateDto.getCountry()).orElseThrow(ResourceNotFoundException::new);
//            userCountryRepository.delete(userCountry);
//        }
//        else if(content.equals("language")){
//            UserLanguage userLanguage = userLanguageRepository.findUserLanguageByUserIdAndCountry(id, updateDto.getLanguage()).orElseThrow(ResourceNotFoundException::new);
//            userLanguageRepository.delete(userLanguage);
//        }
//        else if(content.equals("hope-language")) {
//            UserHopeLanguage userHopeLanguage = userHopeLanguageRepository.findUserHopeLanguageByUserIdAndCountry(id, updateDto.getHopeLanguage()).orElseThrow(ResourceNotFoundException::new);
//            userHopeLanguageRepository.delete(userHopeLanguage);
//        }
//        else if(content.equals("facebook-url")){
//            User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//            user.changeFaceBookUrl("");
//        }
//        else if(content.equals("instagram-url")){
//            User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//            user.changeInstagramUrl("");
//        }
//        else if(content.equals("image-url")){
//            User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//            user.changeImageUrl("");
//        }
//
//
//    }



}
