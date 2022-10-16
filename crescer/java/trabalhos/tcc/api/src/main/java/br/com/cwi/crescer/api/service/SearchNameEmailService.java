package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.SearchNameEmailRequest;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.Friendship;
import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.repository.FriendshipRepository;
import br.com.cwi.crescer.api.repository.UserAccountRepository;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchNameEmailService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public List<UserAccountResponse> search(SearchNameEmailRequest request) {
        UserAccount user = findUserAuthenticatedService.getUser();
        List<UserAccount> usersListFilteredByName =  userAccountRepository
                .filterAllPeopleEmailName(request.getSearch(), user.getUserId());

        List<Friendship> friendships = friendshipRepository
                .filterFriendsWithoutRelationByUser(user.getUserId());

        List <UserAccount> userFriends = new ArrayList<>();

        friendships.stream().map(friendship -> friendship.getUserAccount())
                .filter(userAccount -> userAccount!=user)
                .forEach(userAccount -> userFriends.add(userAccount));

        friendships.stream().map(friendship -> friendship.getUserFriendShip())
                .filter(userAccount -> userAccount!=user)
                .forEach(userAccount -> userFriends.add(userAccount));



        return usersListFilteredByName.stream()
                .filter(userAccount -> !userFriends.contains(userAccount))
                .map(userAccount -> userResponseMapper.toResponse(userAccount))
                .collect(Collectors.toList());

    }
}
