package com.project.chawchaw.dto.chat;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDto implements Serializable {
    private String roomId;
    private String name;

//    public static ChatRoomDto create(String name) {
//        ChatRoomDto chatRoom = new ChatRoomDto();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }
}