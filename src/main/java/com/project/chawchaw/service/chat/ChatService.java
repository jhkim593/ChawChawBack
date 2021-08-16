package com.project.chawchaw.service.chat;

import com.project.chawchaw.dto.chat.ChatDto;
import com.project.chawchaw.dto.chat.ChatMessageDto;

import com.project.chawchaw.entity.ChatRoom;
import com.project.chawchaw.entity.ChatRoomUser;
import com.project.chawchaw.entity.User;
import com.project.chawchaw.exception.UserNotFoundException;
import com.project.chawchaw.repository.chat.ChatMessageRepository;
import com.project.chawchaw.repository.chat.ChatRoomRepository;
import com.project.chawchaw.repository.chat.ChatRoomUserRepository;
import com.project.chawchaw.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;

    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final ChatSubService redisSubscriber;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";

    private Map<Long, ChannelTopic> topics=new HashMap<>();


    public void publish(ChannelTopic topic, ChatMessageDto message) {


        redisTemplate.convertAndSend(topic.getTopic(), message);
//        redisTemplate.getStringSerializer(topic.getTopic());
        chatMessageRepository.createChatMessage(message);
    }

    //chatroom
    @Transactional
    public List<ChatDto> createRoom(Long toUserId, Long fromUserId){
        User toUser=userRepository.findById(toUserId).orElseThrow(UserNotFoundException::new);
        User fromUser=userRepository.findById(fromUserId).orElseThrow(UserNotFoundException::new);

        ChatRoom chatRoom=null;
        Optional<ChatRoomUser> findChatRoomUser = chatRoomUserRepository.findByToUserIdAndFromUserId(toUserId, fromUserId);
        if(!findChatRoomUser.isPresent()) {
           chatRoom = chatRoomRepository.save(ChatRoom.createChatRoom(UUID.randomUUID().toString()));
            chatRoomUserRepository.save(ChatRoomUser.createChatRoomUser(chatRoom,toUser,fromUser));
            chatMessageRepository.createChatMessage(new ChatMessageDto(chatRoom.getId(),fromUser.getName(),fromUser.getName()+"님이 입장하셨습니다.", LocalDateTime.now()));
        }
        else{
           chatRoom=findChatRoomUser.get().getChatRoom();

        }
        List<ChatDto> chatDtos=new ArrayList<>();
       chatDtos.add(new ChatDto(chatRoom.getId(),toUser.getName(),toUser.getImageUrl(),
               chatMessageRepository.findChatMessageByRoomId(chatRoom.getId())));

        List<ChatRoomUser> chatRoomUserByUserId = chatRoomUserRepository.findByChatRoomUserByUserId(fromUserId);
        for(int i=0;i<chatRoomUserByUserId.size();i++){
            ChatRoomUser chatRoomUser = chatRoomUserByUserId.get(i);
            if(chatRoomUser.getChatRoom().getId()==(chatRoom.getId()))continue;
            if(chatRoomUser.getFromUser().getId()==(fromUser.getId())){
                ChatDto chatDto=new ChatDto(chatRoomUser.getChatRoom().getId(),chatRoomUser.getToUser().getName(),chatRoomUser.getToUser().getImageUrl(),
                        chatMessageRepository.findChatMessageByRoomId(chatRoomUser.getChatRoom().getId()));
                chatDtos.add(chatDto);

            }
            else {
                ChatDto chatDto=new ChatDto(chatRoomUser.getChatRoom().getId(),chatRoomUser.getFromUser().getName(),chatRoomUser.getFromUser().getImageUrl(),
                        chatMessageRepository.findChatMessageByRoomId(chatRoomUser.getChatRoom().getId()));
                chatDtos.add(chatDto);

            }
        }
        return chatDtos;

    }
    public void enterChatRoom(Long roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(String.valueOf(roomId));
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }

    public ChannelTopic getTopic(Long roomId) {
        return topics.get(roomId);
    }


}