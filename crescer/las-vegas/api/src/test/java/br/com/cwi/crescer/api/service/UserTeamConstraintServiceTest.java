package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserTeamConstraintServiceTest {
    @InjectMocks
    private UserTeamConstraintService userTeamConstraintService;

    @Test
    @DisplayName("Should throw an error if user is already part of the team")
    void verifyIfUserIsAlreadyInTeam() {
        UserAccount userAccount = UserAccountFactory.get();
        Team team = TeamFactory.getBuilder()
                .userAccountList(List.of(userAccount)).build();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userTeamConstraintService.verifyIfUserIsAlreadyInTeam(team,userAccount);
        });

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"User is already included in team\"", exception.getMessage());


    }

    @Test
    @DisplayName("Should throw an error if user is not already part of the team")
    void verifyIfUserIsNotInTeam() {
        UserAccount userAccount = UserAccountFactory.get();
        Team team = TeamFactory.getBuilder()
                .userAccountList(new ArrayList<>())
                .build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            userTeamConstraintService.verifyIfUserIsNotInTeam(team,userAccount);
        });

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"User is not included in team\"", exception.getMessage());

    }
}