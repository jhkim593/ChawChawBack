package com.project.chawchaw.repository.user;

import com.project.chawchaw.dto.user.UserSearch;
import com.project.chawchaw.dto.user.UsersDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {
    Slice<UsersDto> users(Long lastRegistedShoesId , Pageable pageable, UserSearch userSearch, String school);
    List<UsersDto> usersList( UserSearch userSearch, String school);


}
