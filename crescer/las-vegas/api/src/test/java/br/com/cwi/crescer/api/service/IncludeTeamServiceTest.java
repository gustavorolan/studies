package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.TeamResponse;
import br.com.cwi.crescer.api.mapper.TeamMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IncludeTeamServiceTest {
    @InjectMocks
    private IncludeTeamService includeTeamService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Mock
    private FindAttachmentWithThrow findAttachmentWithThrow;

    @Captor
    private ArgumentCaptor<Team> argumentCaptor;

    @Test
    @DisplayName("Should include a team")
    void includeTeam() {
        TeamRequest teamRequest = TeamFactory.getTeamRequest();
        UserAccount userAccount = UserAccountFactory.get();
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        Team team = TeamFactory.getBuilder()
                .userAccountList(new ArrayList<>())
                .build();

        Team teamSaved = TeamFactory.getBuilder()
                .creator(userAccount)
                .courses(new ArrayList<>())
                .userAccountList(List.of(userAccount))
                .image(attachment)
                .build();

        TeamResponse teamResponse = TeamFactory.getBuilderTeamResponse()
                .image(attachmentResponseData)
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findAttachmentWithThrow.findByIdWithException(attachment.getId())).thenReturn(attachment);
        Mockito.when(teamMapper.toEntity(teamRequest)).thenReturn(team);
        Mockito.when(teamMapper.toResponse(team)).thenReturn(teamResponse);

        TeamResponse result = includeTeamService.includeTeam(teamRequest, attachment.getId());

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findAttachmentWithThrow).findByIdWithException(attachment.getId());
        Mockito.verify(teamMapper).toEntity(teamRequest);
        Mockito.verify(teamMapper).toResponse(team);
        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(teamRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(teamSaved,argumentCaptor.getValue());
        Assertions.assertEquals(teamResponse,result);

    }

    @Test
    @DisplayName("Should include user in some team")
    void includeUserInTeam() {
        UserAccount userAccount = UserAccountFactory.get();
        Attachment attachment = AttachmentFactory.getAttachment();
        TeamRequest teamRequest = TeamFactory.getTeamRequest();
        Team team = TeamFactory.getBuilder()
                .userAccountList(new ArrayList<>())
                .build();
        TeamResponse teamResponse = TeamFactory.getTeamResponse();

        Team teamSaved = TeamFactory.getBuilder()
                .creator(userAccount)
                .courses(new ArrayList<>())
                .userAccountList(List.of(userAccount))
                .image(attachment)
                .build();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findAttachmentWithThrow.findByIdWithException(attachment.getId()))
                .thenReturn(attachment);
        Mockito.when(teamMapper.toEntity(teamRequest))
                .thenReturn(team);
        Mockito.when(teamMapper.toResponse(team))
                .thenReturn(teamResponse);

        TeamResponse result = includeTeamService.includeTeam(teamRequest, attachment.getId());

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findAttachmentWithThrow).findByIdWithException(attachment.getId());
        Mockito.verify(teamMapper).toEntity(teamRequest);
        Mockito.verify(teamMapper).toResponse(team);
        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(permissionValidator).validateManagerPermission(userAccount);
        Mockito.verify(teamRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(teamSaved,argumentCaptor.getValue());
    }
}