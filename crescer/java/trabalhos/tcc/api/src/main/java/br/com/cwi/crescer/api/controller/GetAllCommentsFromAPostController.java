package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.GetAllCommentsFromAPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post/{id}/comments")
public class GetAllCommentsFromAPostController {
    @Autowired
    private GetAllCommentsFromAPostService getAllCommentsFromAPostService;
    @GetMapping
    public List<CommentResponse> getAll(@PathVariable Long id){
        return getAllCommentsFromAPostService.get(id);
    }
}
