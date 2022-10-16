package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.RequestFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.RequestFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/requestNewFriendship")
public class RequestFriendshipController {
    @Autowired
    private RequestFriendshipService requestFriendshipService;
    @PostMapping
    public PutAndPostResponse requestFriendship (@Valid @RequestBody RequestFriendshipRequest request){
        System.out.println(request);
        return requestFriendshipService.request(request);
    }
}
