package br.com.cwi.crescer.api.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class UserWithPostsResponse {
    private UserAccountResponse userAccountResponse;

    private List<PostResponseWithUser> postResponseList;
}

