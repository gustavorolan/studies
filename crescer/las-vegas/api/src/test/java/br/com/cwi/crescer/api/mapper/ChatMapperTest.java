package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.controller.response.MessageResponse;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.util.ChatFactory;
import br.com.cwi.crescer.api.util.MessageFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChatMapperTest {

    @InjectMocks
    private ChatMapper chatMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MessageMapper messageMapper;

    @Test
    @DisplayName("Should convert chat to response")
    void toResponse() {
        Chat chatFactory = ChatFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        MessageResponse messageResponse = MessageFactory.getMessageResponse();
        ChatResponse chatResponse = ChatFactory.getChatResponse();
        chatResponse.setMessageList(List.of(messageResponse));


        Mockito.when(userMapper.toResponse(chatFactory.getUserOne())).thenReturn(userResponse);
        Mockito.when(userMapper.toResponse(chatFactory.getUserTwo())).thenReturn(userResponse);
        Mockito.when(messageMapper.listMessageToResponse(chatFactory.getMessageList()))
                .thenReturn(List.of(messageResponse));

        ChatResponse result = chatMapper.toResponse(chatFactory);

        Mockito.verify(userMapper).toResponse(chatFactory.getUserOne());
        Mockito.verify(userMapper).toResponse(chatFactory.getUserTwo());
        Mockito.verify(messageMapper).listMessageToResponse(chatFactory.getMessageList());

        Assertions.assertEquals(chatResponse,result);
    }
}