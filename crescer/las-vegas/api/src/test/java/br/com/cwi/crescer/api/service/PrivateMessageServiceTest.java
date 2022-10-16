package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.MessageRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.model.Message;
import br.com.cwi.crescer.api.repository.ChatRepository;
import br.com.cwi.crescer.api.repository.MessageRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.ChatFactory;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PrivateMessageServiceTest {
    @InjectMocks
    private PrivateMessageService privateMessageService;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private FindChatOrCreate findChatOrCreate;

    @Mock
    private LocalDateAndTimeService localDateAndTimeService;

    @Captor
    private ArgumentCaptor<Chat> captor;

    @Test
    @DisplayName("Should create a message in chat")
    void send() {
        MessageRequest request = MessageRequest.builder()
                .message("message")
                .receiverName("name@email.com")
                .build();

        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();
        UserAccount userAccount = UserAccountFactory.get();
        UserAccount userAccountReceiver = UserAccountFactory.get();

        Message messageFinal = Message.builder()
                .dateTime(dateTime)
                .senderName(userAccount.getEmail())
                .receiverName(request.getReceiverName())
                .messageToSend(request.getMessage())
                .build();

        Chat chat = ChatFactory.getChatBuilder()
                .userTwo(userAccountReceiver)
                .userOne(userAccount)
                .messageList(new ArrayList<>())
                .build();

        messageFinal.setChat(chat);

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findUserWithThrow.findByEmailWithException(request.getReceiverName()))
                .thenReturn(userAccountReceiver);
        Mockito.when(localDateAndTimeService.getLocalDateTime())
                .thenReturn(dateTime);
        Mockito.when(findChatOrCreate.findChatOrCreate(userAccount,userAccountReceiver))
                .thenReturn(chat);

        ResponseMessage result = privateMessageService.send(request);

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findUserWithThrow).findByEmailWithException(request.getReceiverName());
        Mockito.verify(findChatOrCreate).findChatOrCreate(userAccount,userAccountReceiver);
        Mockito.verify(localDateAndTimeService).getLocalDateTime();
        Mockito.verify(simpMessagingTemplate)
                .convertAndSendToUser(messageFinal.getReceiverName(),"/chat",messageFinal);
        Mockito.verify(chatRepository).save(captor.capture());

        Chat value = captor.getValue();

        Assertions.assertEquals(chat,value);

    }
}