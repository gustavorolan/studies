package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserTeamConstraintService {
    public void verifyIfUserIsAlreadyInTeam(Team team, UserAccount userAccount) {
        if(team.getUserAccountList().contains(userAccount)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User is already included in team");
        }
    }

    public void verifyIfUserIsNotInTeam(Team team, UserAccount userAccount) {
        if(!team.getUserAccountList().contains(userAccount)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User is not included in team");
        }
    }
}
