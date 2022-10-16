package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.GetAllFriendsPostService;
import br.com.cwi.crescer.api.service.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user")
public class GetUserController {
    @Autowired
    private GetUserService getUserService;

    @GetMapping
    public UserAccountResponse get(){
        return getUserService.get();
    }
}
