package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.UpdateTeamRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateTeamServiceTest {

    @InjectMocks
    private UpdateTeamService updateTeamService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private VerifyImageParameterService verifyImageParameterService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Captor
    private ArgumentCaptor<Team> argumentCaptor;

    @Test
    void update() {

        Team team = TeamFactory.get();
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .team(team).build();
        Attachment attachment = AttachmentFactory.getAttachment();
        UpdateTeamRequest request = UpdateTeamRequest.builder()
                .description("new desc")
                .name("new name")
                .build();

        Team teamSaved = TeamFactory.getBuilder()
                .description("new desc")
                .name("new name")
                .image(attachment)
                .build();

        ResponseMessage responseMessage = ResponseMessage.builder().response("You've updated successfully").build();


        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(verifyImageParameterService.verifyImageParameter(attachment.getId()))
                .thenReturn(attachment);

        ResponseMessage update = updateTeamService.update(request, attachment.getId());

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(verifyImageParameterService).verifyImageParameter(attachment.getId());
        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(permissionValidator).validateManagerPermission(userAccount);
        Mockito.verify(teamRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(teamSaved,argumentCaptor.getValue());
        Assertions.assertEquals(responseMessage,update);
    }
}