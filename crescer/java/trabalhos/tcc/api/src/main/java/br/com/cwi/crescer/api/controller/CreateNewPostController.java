package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.CreateNewPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.CreateNewPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post/create")
public class CreateNewPostController {
    @Autowired
    private CreateNewPostService createNewPostService;
    @PostMapping
    public PutAndPostResponse createPost(@Valid @RequestBody CreateNewPostRequest request){
        return createNewPostService.post(request);
    }
}
