package com.project.chawchaw.dto.alarm;

import com.project.chawchaw.dto.chat.ChatMessageDto;
import com.project.chawchaw.dto.follow.FollowAlarmDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDto {

    List<ChatMessageDto>messages;
    List<FollowAlarmDto>follows;
}
