package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.response.MessageResponse;
import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.model.Message;

public class MessageFactory {
    public static Message.MessageBuilder getMessageBuilder(){
        return Message.builder()
                .id(1L)
                .receiverName("name")
                .senderName("name")
                .messageToSend("message");
    }
    public static Message getMessage(){
        return getMessageBuilder().build();
    }

    public static MessageResponse.MessageResponseBuilder getMessageResponseBuilder(){
        return MessageResponse.builder()
                .id(1L)
                .receiverName("name")
                .senderName("name")
                .message("message");
    }
    public static MessageResponse getMessageResponse(){
        return getMessageResponseBuilder().build();
    }

    public static MyMessagesResponse.MyMessagesResponseBuilder getMyMessagesResponseBuilder(){
        return MyMessagesResponse.builder()
                .fullName("name")
                .email("name@email.com")
                .assessment(5)
                .id(1L)
                .read(false);
    }
}
