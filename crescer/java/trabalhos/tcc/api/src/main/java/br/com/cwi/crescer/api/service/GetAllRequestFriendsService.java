package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
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
public class GetAllRequestFriendsService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public List<UserAccountResponse> get() {
        UserAccount user = findUserAuthenticatedService.getUser();
        List<Friendship> friendships = friendshipRepository
                .filterFriendsByUser(user.getUserId(), Relation.NOT_FRIENDS);

        List <UserAccountResponse> userRequestFriends = new ArrayList<>();

        friendships.stream().map(friendship -> friendship.getUserAccount())
                .filter(userAccount -> userAccount!=user)
                .forEach(userAccount -> userRequestFriends.add(userResponseMapper.toResponse(userAccount)));

        return userRequestFriends;
    }
}
