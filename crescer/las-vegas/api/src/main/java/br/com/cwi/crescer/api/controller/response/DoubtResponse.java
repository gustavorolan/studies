package br.com.cwi.crescer.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoubtResponse {
    private Long id;

    private String description;

    private Boolean finished;

    private Boolean active;

    private UserResponse author;

    private AttachmentResponseData image;

    private List<CommentResponse> comments;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateTimeCreation;
}
