package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.CreateNewPostRequest;
import br.com.cwi.crescer.api.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateNewPostMapper {
    public Post toEntity(CreateNewPostRequest request) {
        Post post = Post.builder()
                .postImg(request.getPostImg())
                .postText(request.getPostText())
                .privatePost(request.isPrivatePost())
                .userAccountList(new ArrayList<>())
                .build();

        return post;
    }
}
