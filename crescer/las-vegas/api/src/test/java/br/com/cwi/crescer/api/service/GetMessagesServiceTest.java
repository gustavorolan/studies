package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.mapper.MyMessagesMapper;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
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
class GetMessagesServiceTest {

    @InjectMocks
    private  GetMessagesService getMessagesService;

    @Mock
    private FindChatOrCreate findChatOrCreate;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private MyMessagesMapper myMessagesMapper;

    @Test
    @DisplayName("Should return UserResponse with status of read message or not")
    void firstScenario() {
        UserAccount userAccountOne = UserAccountFactory.getBuilder().id(2L).build();
        UserAccount userAccountTwo = UserAccountFactory.get();
        Chat chatOne = ChatFactory.getChatBuilder()
                .read(true)
                .userOne(userAccountOne)
                .userTwo(userAccountTwo)
                .build();

        MyMessagesResponse build = MessageFactory.getMyMessagesResponseBuilder()
                .id(1L)
                .build();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccountOne);
        Mockito.when(findChatOrCreate.findChatOrCreateMessages(userAccountOne)).thenReturn(List.of(chatOne));
        Mockito.when(myMessagesMapper.toResponse(chatOne.getUserTwo(),chatOne)).thenReturn(build);

        List<MyMessagesResponse> myMessagesResponses = getMessagesService.get();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findChatOrCreate).findChatOrCreateMessages(userAccountOne);
        Mockito.verify(myMessagesMapper).toResponse(chatOne.getUserTwo(),chatOne);

        Assertions.assertEquals(List.of(build),myMessagesResponses );

    }

    @Test
    @DisplayName("Should return UserResponse with status of read message or not")
    void secondScenario() {
        UserAccount userAccountOne = UserAccountFactory.getBuilder().id(2L).build();
        UserAccount userAccountTwo = UserAccountFactory.get();
        Chat chatOne = ChatFactory.getChatBuilder()
                .read(true)
                .userOne(userAccountOne)
                .userTwo(userAccountTwo)
                .build();

        MyMessagesResponse build = MessageFactory.getMyMessagesResponseBuilder()
                .id(1L)
                .build();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccountTwo);
        Mockito.when(findChatOrCreate.findChatOrCreateMessages(userAccountTwo)).thenReturn(List.of(chatOne));
        Mockito.when(myMessagesMapper.toResponse(chatOne.getUserOne(),chatOne)).thenReturn(build);

        List<MyMessagesResponse> myMessagesResponses = getMessagesService.get();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findChatOrCreate).findChatOrCreateMessages(userAccountTwo);
        Mockito.verify(myMessagesMapper).toResponse(chatOne.getUserOne(),chatOne);

        Assertions.assertEquals(List.of(build),myMessagesResponses );

    }
}