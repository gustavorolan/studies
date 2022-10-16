package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.LikeAPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.LikeAPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post/like")
public class LikeAPostController {
    @Autowired
    private LikeAPostService likeAPostService;
    @PostMapping
    public PutAndPostResponse like (@Valid @RequestBody LikeAPostRequest request){
        return likeAPostService.like(request);
    }
}
