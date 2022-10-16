package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.UndoFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Relation;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.service.finders.UserAccountFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UndoFriendshipService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private UserAccountFinderById userAccountFinderById;
    @Autowired
    private FriendshipRepository friendshipRepository;

    public PutAndPostResponse undo(@Valid UndoFriendshipRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();
        UserAccount userToUndo = userAccountFinderById
                .findByIdWithException(request.getIdToUndoFriendShip());
        Friendship friendship = friendshipRepository
               .filterFriendsByUserToUndoFriendship(user.getUserId(),userToUndo.getUserId())
               .get(0);
        friendship.setRelation(Relation.BLOCKED);
        friendshipRepository.save(friendship);
        return PutAndPostResponse.builder()
                .response("Voce acabou de excluir "+ userToUndo.getUserName())
                .build();
    }
}
