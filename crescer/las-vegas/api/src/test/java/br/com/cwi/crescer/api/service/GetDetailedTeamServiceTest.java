package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.TeamCompleteResponse;
import br.com.cwi.crescer.api.mapper.TeamMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.TeamFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDetailedTeamServiceTest {

    @InjectMocks
    private GetDetailedTeamService getDetailedTeamService;

    @Mock
    private TeamMapper teamMapper;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("Should return team detailed")
    void getDetailedTeam() {
        Team team = TeamFactory.get();
        TeamCompleteResponse teamCompleteResponse = TeamFactory.getTeamCompleteResponse();
        UserAccount userAccount = UserAccount.builder().team(team).build();

        Mockito.when(userAccountAuthenticatedService.get())
                .thenReturn(userAccount);
        Mockito.when(teamMapper.toResponseWithUsersAndRoadmaps(team))
                .thenReturn(teamCompleteResponse);


        TeamCompleteResponse result = getDetailedTeamService.getDetailedTeam();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(teamMapper).toResponseWithUsersAndRoadmaps(team);

        Assertions.assertEquals(teamCompleteResponse,result);
    }
}