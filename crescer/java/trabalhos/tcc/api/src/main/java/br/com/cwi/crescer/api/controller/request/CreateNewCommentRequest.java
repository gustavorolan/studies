package br.com.cwi.crescer.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateNewCommentRequest {
    @NotNull
    private Long postId;
    @NotEmpty
    private String comment;
}
