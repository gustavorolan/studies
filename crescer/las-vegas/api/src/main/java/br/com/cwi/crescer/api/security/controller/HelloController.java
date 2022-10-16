package br.com.cwi.crescer.api.security.controller;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.LoadUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private LoadUserInfoService userMapper;

    @GetMapping("/jwt")
    public String sayHello(@AuthenticationPrincipal Jwt jwt) {
        UserAccount user = userMapper.getUserFromJwt(jwt);
        return "Hello " + user.getIdentifier();
    }

    @GetMapping("/user")
    public UserAccount viewUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return userMapper.getUserFromJwt(jwt);
    }

}
