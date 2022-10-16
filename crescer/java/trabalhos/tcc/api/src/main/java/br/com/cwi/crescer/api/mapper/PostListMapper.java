package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.controller.response.PostResponseWithUser;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostListMapper {
    @Autowired
    private UserResponseMapper userResponseMapper;
    public PostResponse toResponse(Post post){
         List<UserAccountResponse> userAccountResponseList =post.getUserAccountList().stream()
                .map(userAccount -> userResponseMapper.toResponse(userAccount) )
                .collect(Collectors.toList());

        PostResponse postResponse = PostResponse.builder()
                .comments(post.getComments())
                .postId(post.getPostId())
                .postImg(post.getPostImg())
                .postText(post.getPostText())
                .dateTime(post.getDateTime())
                .userAccountResponse(userAccountResponseList)
                .likes(post.getLikes())
                .build();

        return  postResponse;
    }

    public PostResponseWithUser postWithoutUserToResponse(Post post){
        List<UserAccountResponse> userAccountResponseList =post.getUserAccountList().stream()
                .map(userAccount -> userResponseMapper.toResponse(userAccount) )
                .collect(Collectors.toList());

        PostResponseWithUser postResponse = PostResponseWithUser.builder()
                .comments(post.getComments())
                .postId(post.getPostId())
                .postImg(post.getPostImg())
                .postText(post.getPostText())
                .dateTime(post.getDateTime())
                .likes(post.getLikes())
                .userAccountResponseList(userAccountResponseList)

                .build();

        return  postResponse;
    }

}
