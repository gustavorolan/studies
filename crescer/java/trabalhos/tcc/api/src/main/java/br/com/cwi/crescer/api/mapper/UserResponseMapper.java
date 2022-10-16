package br.com.cwi.crescer.api.mapper;


import br.com.cwi.crescer.api.controller.response.UserAccountResponse;

import br.com.cwi.crescer.api.controller.response.UserWithPostsResponse;
import br.com.cwi.crescer.api.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
    public UserAccountResponse toResponse (UserAccount user) {
        UserAccountResponse response = UserAccountResponse.builder()
                .userId(user.getUserId())
                .nickName(user.getNickname())
                .userName(user.getUserName())
                .profileImg(user.getProfileImg())
                .email(user.getEmail())
                .build();

        return response;
    }

    public UserWithPostsResponse toResponseWithPosts (UserAccount user) {

        UserWithPostsResponse response = UserWithPostsResponse.builder()
                .userAccountResponse(toResponse(user))
                .build();

        return response;
    }
}
