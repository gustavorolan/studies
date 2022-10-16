package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.RequestFriendshipRequest;
import br.com.cwi.crescer.api.controller.response.PutAndPostResponse;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Notification;
import br.com.cwi.crescer.api.model.Relation;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.repository.NotificationRepository;
import br.com.cwi.crescer.api.service.finders.UserAccountFinderById;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import br.com.cwi.crescer.api.validator.VerifyIfAlreadyExistRelationValidator;
import br.com.cwi.crescer.api.validator.VerifyIfUserIsTryingToAddHimselfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RequestFriendshipService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private UserAccountFinderById userAccountFinderById;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private VerifyIfUserIsTryingToAddHimselfValidator verifyIfUserIsTryingToAddHimselfValidator;
    @Autowired
    private VerifyIfAlreadyExistRelationValidator verifyIfAlreadyExistRelationValidator;

    public PutAndPostResponse request(RequestFriendshipRequest request) {
        verifyIfUserIsTryingToAddHimselfValidator.verifyIfUserIsTryingToAddHimself(request.getFriendId());

        UserAccount userToRequest =  userAccountFinderById.findByIdWithException(request.getFriendId());
        UserAccount user = findUserAuthenticatedService.getUser();

        verifyIfAlreadyExistRelationValidator
                .verifyIfUserIsTryingToAddHimself(request.getFriendId(),user.getUserId());

        Friendship friendship =  Friendship.builder()
                .relation(Relation.NOT_FRIENDS)
                .userFriendShip(userToRequest)
                .userAccount(user)
                .build();

        friendshipRepository.save(friendship);

        Notification notification = Notification
                .builder()
                .notification(user.getUserName()+" solicitou sua amizade")
                .userAccount(userToRequest)
                .build();


        userToRequest.getNotificationList().add(notification);

        notificationRepository.save(notification);

        return PutAndPostResponse.builder()
                .response("Você solicitou a amizade do(a)"+ userToRequest.getUserName())
                .build();
    }
}
