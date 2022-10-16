package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.MessageResponse;
import br.com.cwi.crescer.api.model.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    public MessageResponse toResponse(Message message
    ){
        return MessageResponse.builder()
                .id(message.getId())
                .dateTime(message.getDateTime())
                .receiverName(message.getReceiverName())
                .senderName(message.getSenderName())
                .message(message.getMessageToSend())
                .build();
    }

    public List<MessageResponse> listMessageToResponse(List<Message> messages
    ){
        return messages.stream().map(this::toResponse)
                .collect(Collectors.toList());
    }
}
