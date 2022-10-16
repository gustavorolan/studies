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
import java.util.stream.Collectors;

@Service
public class GetFriendsResponseService {
    @Autowired
    private  GetFriendsService getFriendsService;
    @Autowired
    private UserResponseMapper userResponseMapper;

    public List<UserAccountResponse> get() {
        List<UserAccountResponse>userAccountResponseList = getFriendsService.get()
                .stream().map(friend->{
                return userResponseMapper.toResponse(friend);
                }).collect(Collectors.toList());

        return userAccountResponseList;
    }
}
