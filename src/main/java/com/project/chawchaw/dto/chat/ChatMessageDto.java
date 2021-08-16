package com.project.chawchaw.dto.chat;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageDto implements Serializable {


//    private MessageType type; // 메시지 타입
    private Long roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
    private LocalDateTime regDate;
}
