package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.mapper.ChatMapper;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.ChatFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetChatServiceTest {

    @InjectMocks
    private  GetChatService getChatService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private FindChatOrCreate findChatOrCreate;

    @Mock
    private ChatMapper chatMapper;

    @Test
    @DisplayName("Should return a chat between two people")
    void get() {
        String request = "email@email.com";
        UserAccount userAccount = UserAccountFactory.get();
        UserAccount receiver = UserAccountFactory.getBuilder()
                .email(request)
                .id(2L)
                .build();
        Chat chat = ChatFactory.get();
        ChatResponse chatResponse = ChatFactory.getChatResponse();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findUserWithThrow.findByEmailWithException(request)).thenReturn(receiver);
        Mockito.when(findChatOrCreate.findChatOrCreate(userAccount,receiver)).thenReturn(chat);
        Mockito.when(chatMapper.toResponse(chat)).thenReturn(chatResponse);

        ChatResponse response =  getChatService.get(request);

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findUserWithThrow).findByEmailWithException(request);
        Mockito.verify(findChatOrCreate).findChatOrCreate(userAccount,receiver);
        Mockito.verify(chatMapper).toResponse(chat);

        Assertions.assertEquals(chatResponse,response);
    }
}