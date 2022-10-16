package br.com.cwi.crescer.api.controller.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentResponse {
    private String commentText;
    private UserAccountResponse userAccountResponse;
}
