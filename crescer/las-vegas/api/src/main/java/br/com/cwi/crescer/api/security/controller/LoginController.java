package br.com.cwi.crescer.api.security.controller;

import br.com.cwi.crescer.api.controller.response.LoginResponse;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @PostMapping
    public LoginResponse login() {
        UserAccount user = userAccountAuthenticatedService.get();
        return LoginResponse.builder()
                .permission(user.getPermission())
                .usuarioId(user.getId())
                .build();
    }
}
