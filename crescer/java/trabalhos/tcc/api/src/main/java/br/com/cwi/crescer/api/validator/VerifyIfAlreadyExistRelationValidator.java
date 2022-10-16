package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Component
public class VerifyIfAlreadyExistRelationValidator {
    @Autowired
    private FriendshipRepository friendshipRepository;
    public void verifyIfUserIsTryingToAddHimself(Long idUserOne, Long idUserTwo) {
        List<Friendship> friendships =friendshipRepository.filterFriendsByUserToUndoFriendship(idUserOne,idUserTwo);
        if (!friendships.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe essa relação");
        }
    }
}
