package br.com.cwi.crescer.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageResponse {
    private Long id;

    private String senderName;

    private String receiverName;

    private String message;

    @JsonFormat(pattern = "dd/MM/yy HH:mm")
    private LocalDateTime dateTime;
}
