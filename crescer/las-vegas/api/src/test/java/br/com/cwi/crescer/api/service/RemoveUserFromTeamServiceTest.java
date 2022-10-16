package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class RemoveUserFromTeamServiceTest {

    @InjectMocks
    private RemoveUserFromTeamService removeUserFromTeamService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserTeamConstraintService userTeamConstraintService;

    @Mock
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private PermissionValidator permissionValidator;

    @Captor
    private ArgumentCaptor<UserAccount> userAccountArgumentCaptor;

    @Test
    @DisplayName("Should remove a user from  team")
    void removeUserFromTeam() {
        UserAccount userAccount = UserAccountFactory.get();

        ArrayList<UserAccount> accountArrayList = new ArrayList<>();
        accountArrayList.add(userAccount);

        UserResponse userResponse = UserAccountFactory.getUserResponse();

        Team team = TeamFactory.getBuilder()
                .userAccountList(accountArrayList)
                .build();

        UserAccount adm = UserAccountFactory.getBuilder()
                .team(team)
                .id(2L).build();

        userAccount.setTeam(team);

        UserAccount expected = UserAccount.builder().team(null).build();


        Mockito.when(findUserWithThrow.findByIdAndActiveWithException(userAccount.getId(),true))
                .thenReturn(userAccount);
        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(adm);
        Mockito.when(userMapper.toResponse(userAccount)).thenReturn(userResponse);

        UserResponse result = removeUserFromTeamService.removeUserFromTeam(userAccount.getId());

        Mockito.verify(findUserWithThrow).findByIdAndActiveWithException(userAccount.getId(),true);
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(userMapper).toResponse(userAccount);
        Mockito.verify(invalidateSelfOpperationValidator).invalidateSelfOpperation(userAccount);
        Mockito.verify(userTeamConstraintService).verifyIfUserIsNotInTeam(team, userAccount);
        Mockito.verify(userMapper).toResponse(userAccount);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(adm);
        Mockito.verify(userAccountRepository).save(userAccountArgumentCaptor.capture());

        Assertions.assertEquals(expected.getTeam(),userAccountArgumentCaptor.getValue().getTeam());
        Assertions.assertEquals(userResponse,result);

    }
}