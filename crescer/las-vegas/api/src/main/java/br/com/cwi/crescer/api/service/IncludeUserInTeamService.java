package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeUserInTeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamConstraintService userTeamConstraintService;

    @Autowired
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public ResponseMessage includeUserInTeam(Long idUser) {
        UserAccount userAccount = findUserWithThrow.findByIdAndActiveWithException(idUser, true);

        invalidateSelfOpperationValidator.invalidateSelfOpperation(userAccount);

        Team team = userAccountAuthenticatedService.get().getTeam();

        userTeamConstraintService.verifyIfUserIsAlreadyInTeam(team, userAccount);

        team.getUserAccountList().add(userAccount);
        userAccount.setTeam(team);

        teamRepository.save(team);

        return ResponseMessage.builder().response("You've included successfully").build();

    }
}
