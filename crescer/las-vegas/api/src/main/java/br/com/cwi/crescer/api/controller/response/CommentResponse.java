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
public class CommentResponse {
    private Long id;

    private String description;

    private Boolean isRightResponse;

    private Boolean active;

    private UserResponse author;

    private AttachmentResponseData image;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateTimeCreation;
}