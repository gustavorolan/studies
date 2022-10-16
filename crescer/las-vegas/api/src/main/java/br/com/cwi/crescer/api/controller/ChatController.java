package br.com.cwi.crescer.api.controller;
import br.com.cwi.crescer.api.controller.response.ChatResponse;
import br.com.cwi.crescer.api.controller.response.MyMessagesResponse;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.controller.request.MessageRequest;
import br.com.cwi.crescer.api.service.GetChatService;
import br.com.cwi.crescer.api.service.GetMessagesService;
import br.com.cwi.crescer.api.service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private GetChatService getChatService;

    @Autowired
    private GetMessagesService getMessagesService;

    @Autowired
    private PostReadService postReadService;

    @PostMapping("/me/privateMessage")
    public ResponseMessage recMessage(@Valid @RequestBody MessageRequest message){
        return privateMessageService.send(message);
    }

    @GetMapping("/me/chat/{email}")
    public ChatResponse getChat(@PathVariable String email){
        return getChatService.get(email);
    }


    @GetMapping("/me/messages")
    public List<MyMessagesResponse> getMessages(){
        return getMessagesService.get();
    }

    @PostMapping("/me/messages/{email}/read")
    public ResponseMessage postRead(@PathVariable String email){
        return postReadService.post(email);
    }
}
