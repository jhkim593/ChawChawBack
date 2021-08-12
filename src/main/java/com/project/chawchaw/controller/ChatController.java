package com.project.chawchaw.controller;
// import 생략...

import com.project.chawchaw.dto.chat.ChatMessageDto;
import com.project.chawchaw.dto.chat.ChatRoom;
import com.project.chawchaw.repository.ChatRoomRepository;
import com.project.chawchaw.service.chat.ChatPubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller

public class ChatController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatPubService redisPublisher;

    //채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/chat";
    }


    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public ResponseEntity<List<ChatRoom>> room() {
        return new ResponseEntity(chatRoomRepository.findAllRoom(), HttpStatus.OK);
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ResponseEntity<ChatRoom> createRoom(@RequestParam String name) {
        return new ResponseEntity(chatRoomRepository.createChatRoom(name),HttpStatus.OK);
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseEntity<ChatRoom> roomInfo(@PathVariable String roomId) {
        return new ResponseEntity(chatRoomRepository.findRoomById(roomId),HttpStatus.OK);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "chat/roomdetail";
    }
}