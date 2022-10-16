package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetAllUsersNotInTeamService {
    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @SuppressWarnings("LambdaCanBeReplacedWithAnonymous")
    public Page<UserResponse> getAllUsersNotInTeam(Pageable pageable) {
        Team team = userAccountAuthenticatedService.get().getTeam();

        List<Long> ids = team.getUserAccountList().stream().map(user -> user.getId()).collect(Collectors.toList());

        return findUserWithThrow.findByIdContainsNotWithException(pageable, ids)
                .map(user -> userMapper.toResponse(user));
    }
}
