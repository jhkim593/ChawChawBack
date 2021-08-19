package com.project.chawchaw.dto.chat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatDto {
    private Long roomId;
    private Long senderId;
    private String sender;
    private String imageUrl;
    private List<ChatMessageDto> messages;
}
