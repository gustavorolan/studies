package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.service.GetAllFriendsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/getPostFriends")
public class GetFriendsPostsController {
    @Autowired
    private GetAllFriendsPostService getAllFriendsPostService;

    @GetMapping
    public List<PostResponse> getAll(){
        return getAllFriendsPostService.getAllPostsFromFriends();
    }

}
