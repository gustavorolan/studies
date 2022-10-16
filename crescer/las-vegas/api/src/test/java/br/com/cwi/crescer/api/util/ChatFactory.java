package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.model.Chat;

import java.util.ArrayList;

public class ChatFactory {
    public static Chat get(){
        return getChatBuilder().build();
    }

    public static Chat.ChatBuilder getChatBuilder(){
        return Chat.builder()
                .id(1L)
                .messageList(new ArrayList<>())
                .userOne(UserAccountFactory.get())
                .userTwo(UserAccountFactory.get());
    }

    public static ChatResponse getChatResponse(){
        return getChatResponseBuilder().build();
    }

    public static ChatResponse.ChatResponseBuilder getChatResponseBuilder(){
        return ChatResponse.builder()
                .id(1L)
                .messageList(new ArrayList<>())
                .userOne(UserAccountFactory.getUserResponse())
                .userTwo(UserAccountFactory.getUserResponse());
    }

}
