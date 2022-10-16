package br.com.cwi.crescer.api.controller;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.GetFriendsResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user/getAllFriends")
public class GetFriendsController {
    @Autowired
    private GetFriendsResponseService getFriendsResponseService;

    @GetMapping
    public List<UserAccountResponse> getAll(){
        return getFriendsResponseService.get();
    }

}
