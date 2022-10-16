package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.IncludeUserAccountIntoPostRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.IncludeUserAccountIntoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post/includeNewUser")
public class IncludeUserAccountIntoPostController {

    @Autowired
    private IncludeUserAccountIntoPostService includeUserAccountIntoPostService;

    @PutMapping
    public PutAndPostResponse include( @RequestBody IncludeUserAccountIntoPostRequest request){
        return includeUserAccountIntoPostService.includeUserAccount(request);
    }

}
