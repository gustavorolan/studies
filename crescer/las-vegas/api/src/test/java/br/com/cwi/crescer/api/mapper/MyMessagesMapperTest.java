package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.ChatFactory;
import br.com.cwi.crescer.api.util.MessageFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MyMessagesMapperTest {

    @InjectMocks
    private MyMessagesMapper myMessagesMapper;

    @Test
    void toResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        Chat chat = ChatFactory.get();
        MyMessagesResponse myMessagesResponse = MessageFactory
                .getMyMessagesResponseBuilder()
                .build();

        MyMessagesResponse result = myMessagesMapper.toResponse(userAccount, chat);

        Assertions.assertEquals(myMessagesResponse,result);


    }
}