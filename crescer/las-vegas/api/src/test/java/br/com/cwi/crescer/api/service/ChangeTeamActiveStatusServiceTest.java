package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindTeamWithThrow;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ChangeTeamActiveStatusServiceTest {
    @InjectMocks
    private ChangeTeamActiveStatusService changeTeamActiveStatusService;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private LocalDateAndTimeService localDateAndTimeService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private FindTeamWithThrow findTeamWithThrow;

    @Mock
    private TeamRepository teamRepository;

    @Captor
    private ArgumentCaptor<Team> captor;

    @Test
    @DisplayName("Change team active status")
    void changeTeamActiveStatus() {
        Long id = 1L;
        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();

        UserAccount userAccount = UserAccountFactory.getBuilder()
                .active(true)
                .build();
        Team team = TeamFactory.getBuilder()
                .active(true)
                .build();

        Team teamFinal = TeamFactory.getBuilder()
                .active(false)
                .dateTimeUpdate(dateTime)
                .build();
        userAccount.setTeam(team);

        ResponseMessage response = ResponseMessage.builder()
                .response("You've changed successfully")
                .build();

        Mockito.when(localDateAndTimeService.getLocalDateTime()).thenReturn(dateTime);
        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        ResponseMessage responseMessage = changeTeamActiveStatusService.changeTeamActiveStatus();

        Mockito.verify(localDateAndTimeService).getLocalDateTime();
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(permissionValidator).validateManagerPermission(userAccount);

        Mockito.verify(teamRepository).save(captor.capture());

        Team value = captor.getValue();

        Assertions.assertEquals(response,responseMessage);
        Assertions.assertEquals(teamFinal,value);
    }
}