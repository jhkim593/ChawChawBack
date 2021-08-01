package com.project.chawchaw.service;

import com.project.chawchaw.dto.user.*;
import com.project.chawchaw.entity.*;
import com.project.chawchaw.exception.*;
import com.project.chawchaw.repository.*;
import com.project.chawchaw.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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

    @Value("${file.path}")
    private String fileRealPath;

    private Path diLocation;

    @Transactional
    public UserDto detailUser(Long userId){

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addView();
        for(int i=0 ;i<user.getCountry().size();i++){
            System.out.println("============================================");
            System.out.println(user.getCountry().get(i).getRep());
            System.out.println(user.getCountry().get(i).getCountry().getName());
        }

       UserDto userDto= new UserDto(user);

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
    public String userImageUpload(MultipartFile file,Long id) throws IOException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);


        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + file.getOriginalFilename();

        System.out.println("-=--------------------=======================");
        Path filePath = Paths.get(fileRealPath + uuidFilename);
        try {
            Files.write(filePath, file.getBytes());
            return uuidFilename;
        } catch (IOException e) {
            throw new IOException();
        }


    }
///
    @Transactional
    public String fileUpload(MultipartFile file){
        diLocation = Paths.get(fileRealPath).toAbsolutePath().normalize();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if(fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

            Path targetLocation = diLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch(Exception e) {
            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
        }

    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = diLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            }else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        }catch(MalformedURLException e) {
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
        }
    }







 ////


    @Transactional
    public void userProfileUpdate(UserUpdateDto updateDto, Long id) {





        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);


        Country repCountry = countryRepository.findByName(updateDto.getRepCountry()).orElseThrow(CountryNotFoundException::new);
        Language repLanguage = languageRepository.findByAbbr(updateDto.getRepLanguage()).orElseThrow(LanguageNotFoundException::new);
        Language repHopeLanguage = languageRepository.findByAbbr(updateDto.getRepHopeLanguage()).orElseThrow(LanguageNotFoundException::new);

        UserHopeLanguage userRepHopeLanguage = UserHopeLanguage.createUserHopeLanguage(repHopeLanguage);
        userRepHopeLanguage.changeRep();
        userRepHopeLanguage.addUser(user);
        UserLanguage userRepLanguage = UserLanguage.createUserLanguage(repLanguage);
        userRepLanguage.changeRep();
        userRepLanguage.addUser(user);
        UserCountry userRepCountry = UserCountry.createUserCountry(repCountry);
        userRepCountry.changeRep();
        userRepCountry.addUser(user);

        user.changeRep(repCountry.getName(),repLanguage.getAbbr(),repHopeLanguage.getAbbr());



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
                Language language = languageRepository.findByAbbr(updateDto.getLanguage().get(i)).orElseThrow(LanguageNotFoundException::new);
                UserLanguage userLanguage = UserLanguage.createUserLanguage(language);
                userLanguage.addUser(user);

            }
        }

        if (!updateDto.getHopeLanguage().isEmpty()) {
            userHopeLanguageRepository.deleteByUserId(id);
            for (int i = 0; i < updateDto.getHopeLanguage().size(); i++) {
                Language language = languageRepository.findByAbbr(updateDto.getHopeLanguage().get(i)).orElseThrow(LanguageNotFoundException::new);
                UserHopeLanguage userHopeLanguage = UserHopeLanguage.createUserHopeLanguage(language);
                userHopeLanguage.addUser(user);

            }
        }
        user.changeImageUrl(updateDto.getImageUrl());
        user.changeInstagramUrl(updateDto.getInstagramUrl());
        user.changeFaceBookUrl(updateDto.getFacebookUrl());
        user.changeContent(updateDto.getContent());

    }

    public Boolean deleteImage(String imageUrl, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        File file=new File(fileRealPath+imageUrl
        );
        if(file.exists()){
            if(file.delete()){
                user.changeImageUrl(fileRealPath+"defaultImage_233500392.png");
                return true;
            }
            else return false;
        }
        else return false;
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
