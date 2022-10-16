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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrivateMessageService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FindChatOrCreate findChatOrCreate;

    @Autowired
    private LocalDateAndTimeService localDateAndTimeService;

    public ResponseMessage send (MessageRequest request) {
        LocalDateTime localDateTime = localDateAndTimeService.getLocalDateTime();
        UserAccount userAccount = userAccountAuthenticatedService.get();
        UserAccount userAccountReceiver=  findUserWithThrow
                .findByEmailWithException(request.getReceiverName());

        Chat chat = findChatOrCreate.findChatOrCreate(userAccount,userAccountReceiver);

        chat.setRead(false);
        chat.setLastMessage(localDateTime);

        Message message = Message.builder()
                .dateTime(localDateTime)
                .senderName(userAccount.getEmail())
                .receiverName(request.getReceiverName())
                .messageToSend(request.getMessage())
                .chat(chat)
                .build();


        chat.getMessageList().add(message);
        chatRepository.save(chat);

        simpMessagingTemplate
               .convertAndSendToUser(message.getReceiverName(),"/chat",message);

        return ResponseMessage.builder().response("You message has been sent with success").build();
    }
}
