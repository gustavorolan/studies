package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.mapper.PostListMapper;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllFriendsPostWithPagService {
    @Autowired
    private GetFriendsService getFriendsService;
    @Autowired
    private PostListMapper postListMapper;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;

    public List<PostResponse>  getAllPostsFromFriends(){
        List<PostResponse> postList = new ArrayList<>();
        UserAccount user = findUserAuthenticatedService.getUser();

        user.getPostList().stream().forEach(post -> postList.add(postListMapper.toResponse(post)));

        getFriendsService.get().stream().forEach(userAccount-> {
            userAccount.getPostList().stream().forEach(post-> postList.add(postListMapper.toResponse(post)));
        });

        return postList.stream()
                .sorted((postOne,postTwo)-> postTwo.getDateTime().compareTo(postOne.getDateTime()))
                .collect(Collectors.toList());
    }
}
