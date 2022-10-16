package br.com.cwi.crescer.api.controller.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatResponse {

    Long id;

    UserResponse userOne;

    UserResponse userTwo;

    List<MessageResponse> messageList;
}
