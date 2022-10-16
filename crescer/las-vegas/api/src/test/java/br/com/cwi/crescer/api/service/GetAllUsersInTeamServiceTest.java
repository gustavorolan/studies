package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllUsersInTeamServiceTest {

    @InjectMocks
    private  GetAllUsersInTeamService getAllUsersInTeamService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("Should return all users of a team")
    void getAllUsersInTeam() {
        Team team = TeamFactory.get();
        UserAccount userAccount = UserAccountFactory.getBuilder().team(team).build();
        team.setUserAccountList(List.of(userAccount));

        UserResponse userResponse = UserAccountFactory.getUserResponse();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(userMapper.toResponse(userAccount)).thenReturn(userResponse);

        List<UserResponse> allUsersInTeam = getAllUsersInTeamService.getAllUsersInTeam();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(userMapper).toResponse(userAccount);

        Assertions.assertEquals(List.of(userResponse),allUsersInTeam);
    }
}