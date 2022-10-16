package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.Relation;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetFriendsService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private FriendshipRepository friendshipRepository;


    public List<UserAccount> get() {
        UserAccount user = findUserAuthenticatedService.getUser();
        List<Friendship> friendships = friendshipRepository
                .filterFriendsByUser(user.getUserId(), Relation.FRIENDS);

        System.out.println(friendships);

        List <UserAccount> userFriends = new ArrayList<>();

        friendships.stream().map(friendship -> friendship.getUserAccount())
                .filter(userAccount -> userAccount!=user)
                .forEach(userAccount -> userFriends.add(userAccount));

        friendships.stream().map(friendship -> friendship.getUserFriendShip())
                .filter(userAccount -> userAccount!=user)
                .forEach(userAccount -> userFriends.add(userAccount));

        return userFriends;
    }
}
