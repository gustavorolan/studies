package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.CreateNewUserRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.CreateNewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/createNewUser")
public class CreateNewUserController {
    @Autowired
    private CreateNewUserService createNewUserService;

    @PostMapping
    public PutAndPostResponse create(@Valid @RequestBody CreateNewUserRequest request){
        return createNewUserService.create(request);
    }
}
