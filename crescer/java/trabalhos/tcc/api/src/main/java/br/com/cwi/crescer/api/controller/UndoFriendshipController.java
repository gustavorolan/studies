package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.UndoFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.service.UndoFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/undoFriendship")
public class UndoFriendshipController {
    @Autowired
    private UndoFriendshipService undoFriendshipService;

    @PutMapping
    public PutAndPostResponse undo(@Valid  @RequestBody UndoFriendshipRequest request){
        return undoFriendshipService.undo(request);
    }

}
