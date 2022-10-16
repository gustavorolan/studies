package br.com.cwi.crescer.api.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PostResponseWithUser {
    private Long postId;

    private String postText;

    private String postImg;

    private Integer likes;

    private LocalDateTime dateTime;

    private Integer comments;

    private List<UserAccountResponse> userAccountResponseList;

    private UserAccountResponse userAccountResponse;
}
