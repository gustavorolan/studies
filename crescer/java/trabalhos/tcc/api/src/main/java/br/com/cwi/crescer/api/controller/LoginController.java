package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public PutAndPostResponse login(){
        return PutAndPostResponse
                .builder()
                .response("Você foi logado")
                .build();
    }
}
