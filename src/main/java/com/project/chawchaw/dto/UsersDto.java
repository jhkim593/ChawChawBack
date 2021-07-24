package com.project.chawchaw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String imageUrl;
    private String content;
    private LocalDateTime createDate;
    private Long view;
    private int follows;


}
