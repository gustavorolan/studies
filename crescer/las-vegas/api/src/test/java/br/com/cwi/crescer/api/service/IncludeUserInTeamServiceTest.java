package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class IncludeUserInTeamServiceTest {
    @InjectMocks
    private  IncludeUserInTeamService includeUserInTeamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserTeamConstraintService userTeamConstraintService;

    @Mock
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;


    @Captor
    private ArgumentCaptor<Team> argumentCaptor;



    @Test
    @DisplayName("Include user in some team")
    void testIncludeUserInTeam() {
        Team team = TeamFactory.getBuilder()
                .userAccountList(new ArrayList<>())
                .build();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .response("You've included successfully").build();

        UserAccount userAccountToAdd = UserAccountFactory.getBuilder()
                .id(2L).build();
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .team(team).build();
        TeamFactory.getBuilder().build();

        Team teamSaved = TeamFactory.getBuilder()
                .userAccountList(List.of(userAccountToAdd))
                .build();

        Mockito.when(findUserWithThrow.findByIdAndActiveWithException(userAccountToAdd.getId(), true))
                .thenReturn(userAccountToAdd);
        Mockito.when(userAccountAuthenticatedService.get())
                .thenReturn(userAccount);

        ResponseMessage result = includeUserInTeamService.includeUserInTeam(userAccountToAdd.getId());

        Mockito.verify(findUserWithThrow).findByIdAndActiveWithException(userAccountToAdd.getId(), true);
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(invalidateSelfOpperationValidator).invalidateSelfOpperation(userAccountToAdd);
        Mockito.verify(userTeamConstraintService).verifyIfUserIsAlreadyInTeam(team, userAccountToAdd);
        Mockito.verify(teamRepository).save(argumentCaptor.capture());


        Assertions.assertEquals(teamSaved,argumentCaptor.getValue());
        Assertions.assertEquals(responseMessage,result);
    }
}