package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetAllUsersInTeamService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public List<UserResponse> getAllUsersInTeam() {
        Team team = userAccountAuthenticatedService.get().getTeam();

        return team.getUserAccountList().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }
}
