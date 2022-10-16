package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.mapper.ChatMapper;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetChatService {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private FindChatOrCreate findChatOrCreate;

    @Autowired
    private ChatMapper chatMapper;

    public ChatResponse get(String request) {
        UserAccount userAccount = userAccountAuthenticatedService.get();
        UserAccount userAccountReceiver = findUserWithThrow
                .findByEmailWithException(request);
        Chat chat = findChatOrCreate.findChatOrCreate(userAccount,userAccountReceiver);
        return chatMapper.toResponse(chat);
    }
}
