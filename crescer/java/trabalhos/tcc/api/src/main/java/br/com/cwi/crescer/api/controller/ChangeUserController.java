package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.ChangeUserRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.ChangeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user/changeUser")
public class ChangeUserController {
    @Autowired
    private ChangeUserService changeUserService;

    @PutMapping
    public PutAndPostResponse change( @RequestBody ChangeUserRequest request){
        return changeUserService.change(request);
    }
}
