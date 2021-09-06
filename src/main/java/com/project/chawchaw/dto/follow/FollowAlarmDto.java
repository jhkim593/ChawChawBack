package com.project.chawchaw.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowAlarmDto {
    private FollowType followType;
    private String name;
    private LocalDateTime regDate;
}
