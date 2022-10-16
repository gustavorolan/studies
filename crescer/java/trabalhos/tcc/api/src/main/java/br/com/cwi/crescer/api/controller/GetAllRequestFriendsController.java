package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.GetAllRequestFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/getAllRequestFriends")
public class GetAllRequestFriendsController {
    @Autowired
    private GetAllRequestFriendsService getAllRequestFriendsService;

    @GetMapping
    public List<UserAccountResponse> getAll(){
        return getAllRequestFriendsService.get();
    }

}
