package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.repository.ChatRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostReadService {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private FindChatOrCreate findChatOrCreate;

    @Autowired
    private ChatRepository chatRepository;

    public ResponseMessage post(String email) {
        UserAccount userAccount = userAccountAuthenticatedService.get();
        UserAccount userAccountReceiver = findUserWithThrow
                .findByEmailWithException(email);

        Chat chat = findChatOrCreate.findChatOrCreate(userAccount,userAccountReceiver);

        chat.setRead(true);

        chatRepository.save(chat);

        return ResponseMessage.builder().response("You've read the message").build();
    }
}
