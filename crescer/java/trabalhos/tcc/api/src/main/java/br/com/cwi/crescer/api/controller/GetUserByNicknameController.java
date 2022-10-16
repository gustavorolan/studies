package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.GetUserByNicknameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class GetUserByNicknameController {
    @Autowired
    private GetUserByNicknameService getUserByNicknameService;
    @GetMapping("/{nickname}")
    public UserAccountResponse getByNickName (@PathVariable String nickname){
        return getUserByNicknameService.get(nickname);
    }
}
