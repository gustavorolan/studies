package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.controller.response.UserWithPostsResponse;
import br.com.cwi.crescer.api.service.GetAllCommentsFromAPostService;
import br.com.cwi.crescer.api.service.GetAllLikesFromAPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post/{id}/likes")
public class GetAllLikesController {
    @Autowired
    private GetAllLikesFromAPostService getAllLikesFromAPostService;
    @GetMapping
    public List<UserWithPostsResponse> getAll(@PathVariable Long id){
        return getAllLikesFromAPostService.get(id);
    }
}
