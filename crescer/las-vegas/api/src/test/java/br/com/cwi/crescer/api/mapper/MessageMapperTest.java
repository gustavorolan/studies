package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.MessageResponse;
import br.com.cwi.crescer.api.model.Message;
import br.com.cwi.crescer.api.util.MessageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MessageMapperTest {

    @InjectMocks
    private  MessageMapper messageMapper;


    @Test
    @DisplayName("Should return a MessageResponse")
    void toResponse() {
        Message message = MessageFactory.getMessage();
        MessageResponse messageResponse = MessageFactory.getMessageResponse();

        MessageResponse result = messageMapper.toResponse(message);

        Assertions.assertEquals(messageResponse,result);
    }

    @Test
    @DisplayName("Should return a listMessageToResponse")
    void listMessageToResponse() {
        Message message = MessageFactory.getMessage();
        MessageResponse messageResponse = MessageFactory.getMessageResponse();
        List<Message> messageList = new ArrayList<>();
        messageList.add(message);
        messageList.add(message);
        List<MessageResponse> messageResponseList = new ArrayList<>();
        messageResponseList.add(messageResponse);
        messageResponseList.add(messageResponse);

        List<MessageResponse> result = messageMapper.listMessageToResponse(messageList);

        Assertions.assertEquals(messageResponseList,result);
    }
}