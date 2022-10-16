package br.com.cwi.crescer.api.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PostResponse {

    private Long postId;

    private List<UserAccountResponse> userAccountResponse;

    private String postText;

    private String postImg;

    private Integer likes;

    private LocalDateTime dateTime;

    private Integer comments;
}
