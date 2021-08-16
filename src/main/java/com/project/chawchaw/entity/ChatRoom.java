package com.project.chawchaw.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomUser>chatRoomUsers=new ArrayList<>();

    public static ChatRoom createChatRoom(String name){
        ChatRoom chatRoom=new ChatRoom();
        chatRoom.name=name;
        return chatRoom;
    }
}
