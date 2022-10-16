package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.mapper.MyMessagesMapper;
import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindChatOrCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetMessagesService {
    @Autowired
    private FindChatOrCreate findChatOrCreate;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private MyMessagesMapper myMessagesMapper;

    public List<MyMessagesResponse> get() {
        UserAccount userAccount = userAccountAuthenticatedService.get();
        List<Chat> chatOrCreateMessages = findChatOrCreate.findChatOrCreateMessages(userAccount);
        List<MyMessagesResponse> userAccountList = new ArrayList<>();

        chatOrCreateMessages.forEach(chat ->{
           if (!userAccount.equals(chat.getUserOne()))
               userAccountList.add(myMessagesMapper.toResponse(chat.getUserOne(),chat));
        });

        chatOrCreateMessages.forEach(chat ->{
            if (!userAccount.equals(chat.getUserTwo()))
                userAccountList.add(myMessagesMapper.toResponse(chat.getUserTwo(),chat));
        });

        return userAccountList;
    }
}
