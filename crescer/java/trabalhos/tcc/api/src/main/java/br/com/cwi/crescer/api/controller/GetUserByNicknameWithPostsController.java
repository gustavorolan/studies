package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.UserWithPostsResponse;
import br.com.cwi.crescer.api.service.GetUserWithPostsByNicknameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("friends/{nickname}")
public class GetUserByNicknameWithPostsController {
    @Autowired
    private GetUserWithPostsByNicknameService getUserWithPostsByNickname;
    @GetMapping
    public UserWithPostsResponse getByNickNameWithPosts (@PathVariable String nickname){
        return getUserWithPostsByNickname.get(nickname);
    }
}
