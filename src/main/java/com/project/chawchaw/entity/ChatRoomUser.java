package com.project.chawchaw.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userTo_id")
    private User toUser;

    @ManyToOne
    @JoinColumn(name="userFrom_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name="chatRoom_id")
    private ChatRoom chatRoom;

    public static ChatRoomUser createChatRoomUser(ChatRoom chatRoom, User toUser,User fromUser){
        ChatRoomUser chatRoomUser=new ChatRoomUser();
        chatRoomUser.chatRoom=chatRoom;
        chatRoomUser.toUser=toUser;
        chatRoomUser.fromUser=fromUser;
        return chatRoomUser;
    }



}
