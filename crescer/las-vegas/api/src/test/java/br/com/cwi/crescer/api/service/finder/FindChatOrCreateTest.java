package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.repository.ChatRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
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

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FindChatOrCreateTest {
    @InjectMocks
    private FindChatOrCreate findChatOrCreate;

    @Mock
    private ChatRepository chatRepository;

    @Test
    @DisplayName("Should return chat from repository")
    void findChat() {
        UserAccount userAccount = UserAccountFactory.get();
        Chat chat = ChatFactory.get();

        Mockito.when(chatRepository.findChatByUsers(userAccount,userAccount)).thenReturn(Optional.of(chat));

        Chat result = findChatOrCreate.findChatOrCreate(userAccount, userAccount);

        Mockito.verify(chatRepository).findChatByUsers(userAccount,userAccount);

        Assertions.assertEquals(chat,result);
    }
    @Test
    @DisplayName("Should create chat")
    void create() {
        UserAccount userAccount = UserAccountFactory.get();
        Chat chat = Chat.builder()
                .messageList(new ArrayList<>())
                .userOne(userAccount)
                .userTwo(userAccount)
                .build();

        Mockito.when(chatRepository.findChatByUsers(userAccount,userAccount)).thenReturn(Optional.empty());

        Chat result = findChatOrCreate.findChatOrCreate(userAccount, userAccount);

        Mockito.verify(chatRepository).findChatByUsers(userAccount,userAccount);

        Assertions.assertEquals(chat,result);
    }
}