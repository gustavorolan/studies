package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    public ChatResponse toResponse(Chat chat
    ){
        return ChatResponse.builder()
                .id(chat.getId())
                .userOne(userMapper.toResponse(chat.getUserOne()))
                .userTwo(userMapper.toResponse(chat.getUserTwo()))
                .messageList(messageMapper.listMessageToResponse(chat.getMessageList()))
                .build();
    }

}
