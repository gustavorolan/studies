package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.AcceptFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.AcceptFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/acceptNewFriendship")
public class AcceptFriendshipController {

    @Autowired
    private AcceptFriendshipService acceptFriendshipService;

    @PutMapping
    public PutAndPostResponse accept (@Valid @RequestBody AcceptFriendshipRequest request){
        System.out.println(request);
        return acceptFriendshipService.accept(request);
    }
}
