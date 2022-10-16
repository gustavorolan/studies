package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.AcceptFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Notification;
import br.com.cwi.crescer.api.model.Relation;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.finders.FriendshipFinderById;
import br.com.cwi.crescer.api.service.finders.UserAccountFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import br.com.cwi.crescer.api.service.verifys.VerifyIfAccountLoggedIsRequestFriendAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcceptFriendshipService {
    @Autowired
    private FriendshipFinderById friendshipFinderById;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private VerifyIfAccountLoggedIsRequestFriendAccountService verifyIfAccountLoggedIsRequestFriendAccountService;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private UserAccountFinderById userAccountFinderById;

    public PutAndPostResponse accept(AcceptFriendshipRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();
        UserAccount userToAccept = userAccountFinderById
                .findByIdWithException(request.getFriendshipId());
        Friendship friendship = friendshipRepository
                .filterFriendsByUserToUndoFriendship(user.getUserId(),userToAccept.getUserId())
                .get(0);

        verifyIfAccountLoggedIsRequestFriendAccountService.VerifyIfIsSameLogged(friendship.getUserFriendShip());

        friendship.setRelation(Relation.FRIENDS);

        UserAccount userToNotify = friendship.getUserAccount();

        Notification notification = Notification.builder()
                .notification("Foi aceita a sua  solicitação de amizade")
                .build();

        userToNotify.getNotificationList().add(notification);

        friendshipRepository.save(friendship);

        userAccountRepository.save(userToNotify);


        return PutAndPostResponse.builder().response("Você aceitou a solicitação de amizade").build();
    }
}
