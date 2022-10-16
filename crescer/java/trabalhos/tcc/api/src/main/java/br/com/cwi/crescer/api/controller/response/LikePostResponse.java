package br.com.cwi.crescer.api.controller.response;

import lombok.Data;

@Data
public class LikePostResponse {
    private Long likeId;
    private UserAccountResponse userAccountResponse;
}
