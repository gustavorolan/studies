package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllUsersNotInTeamServiceTest {

    @InjectMocks
    private GetAllUsersNotInTeamService getAllUsersNotInTeamService;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;


    @Test
    @DisplayName("Should get all users not in team")
    void getAllUsersNotInTeam() {
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Team team = TeamFactory.get();
        UserAccount user = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();
        UserAccount outsider = UserAccountFactory.get();

        team.setUserAccountList(List.of(user));
        user.setTeam(team);

        Mockito.when(userAccountAuthenticatedService.get())
                .thenReturn(user);

        Page<UserAccount> page = new PageImpl<UserAccount>(List.of(outsider));

        Mockito.when(findUserWithThrow.findByIdContainsNotWithException(pageable, List.of(user.getId())))
                .thenReturn(page);

        Mockito.when(userMapper.toResponse(outsider))
                .thenReturn(userResponse);

        Page<UserResponse> response = new PageImpl<UserResponse>(List.of(userResponse));

        Page<UserResponse> value = getAllUsersNotInTeamService.getAllUsersNotInTeam(pageable);

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findUserWithThrow).findByIdContainsNotWithException(pageable, List.of(user.getId()));
        Mockito.verify(userMapper).toResponse(outsider);

        Assertions.assertEquals(response, value);
    }
}
