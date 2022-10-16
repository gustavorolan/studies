package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.CreateNewCommentRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.CreateNewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post/comment/create")
public class CreateCommentController {
    @Autowired
    private CreateNewCommentService createNewCommentService;
    @PostMapping
    public PutAndPostResponse createComment(@Valid @RequestBody CreateNewCommentRequest request){
        return createNewCommentService.post(request);
    }
}
