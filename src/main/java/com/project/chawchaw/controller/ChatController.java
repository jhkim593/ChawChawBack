package com.project.chawchaw.controller;
// import 생략...

import com.project.chawchaw.config.jwt.JwtTokenProvider;
import com.project.chawchaw.config.response.DefaultResponseVo;
import com.project.chawchaw.config.response.ResponseMessage;
import com.project.chawchaw.dto.chat.ChatMessageDto;
import com.project.chawchaw.dto.chat.ChatRoomRequestDto;
import com.project.chawchaw.repository.chat.ChatMessageRepository;
import com.project.chawchaw.repository.chat.ChatRoomUserRepository;
import com.project.chawchaw.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller

public class ChatController {

    private final ChatMessageRepository chatRoomRepository;
    private final ChatService chatService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomUserRepository chatRoomUserRepository;

//    //채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "chat/chat";
//    }


    @MessageMapping("/message")
    public void message(@RequestBody ChatMessageDto message) {

        if (message.getRegDate()==null) {
            message.setRegDate(LocalDateTime.now().withNano(0));
        }
//        System.out.println(message.getRegDate());
        chatService.enterChatRoom(message.getRoomId());
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        chatService.publish(chatService.getTopic(message.getRoomId()), message);
    }

    // 모든 채팅방 목록 반환
//    @GetMapping("/rooms")
//    @ResponseBody
//    public ResponseEntity<List<ChatRoomDto>> room() {
//        return new ResponseEntity(chatService.findAllRoom(), HttpStatus.OK);
//    }
    // 채팅방 생성
    @PostMapping("/chat/room")
    @ResponseBody
    public ResponseEntity createRoom(@RequestBody ChatRoomRequestDto requestDto, @RequestHeader("Authorization") String token) {

        Long fromUserId = Long.valueOf(jwtTokenProvider.getUserPk(token));
//        chatRoomUserRepository.isChatRoom(false)
        if(chatRoomUserRepository.isChatRoom(fromUserId,requestDto.getUserId()).isPresent()){

            return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.CHATROOM_FIND_SUCCESS,
                    true,chatService.getChat(fromUserId)), HttpStatus.OK);
        }
        else{
            return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.CHATROOM_CREAT_SUCCESS,
                    true,chatService.createRoom(requestDto.getUserId(), fromUserId)), HttpStatus.CREATED);
        }

    }

    //채팅방 조회
    @GetMapping("/chat")
    @ResponseBody
    public ResponseEntity getChatRoom(@RequestHeader("Authorization") String token) {

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));

        return new ResponseEntity(DefaultResponseVo.res(ResponseMessage.CHATROOM_FIND_SUCCESS,
                true,chatService.getChat(userId)), HttpStatus.OK);
    }


}