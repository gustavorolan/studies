package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Chat;
import br.com.cwi.crescer.api.repository.ChatRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindChatOrCreate {
    @Autowired
    private ChatRepository chatRepository;

    public Chat findChatOrCreate(UserAccount userOne, UserAccount userTwo) {
        return chatRepository
                .findChatByUsers(userOne, userTwo).orElseGet(() ->
                        Chat.builder()
                                .messageList(new ArrayList<>())
                                .userOne(userOne)
                                .userTwo(userTwo)
                                .read(true)
                                .build()
                );
    }

    public List<Chat> findChatOrCreateMessages(UserAccount userOne) {
        Chat build = Chat.builder()
                .messageList(new ArrayList<>())
                .userOne(userOne)
                .userTwo(userOne)
                .read(true)
                .build();
        List<Chat> chatList = List.of(build);
        return chatRepository
                .findMessageByUser(userOne).orElseGet(() -> chatList  );
    }
}