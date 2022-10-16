package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangeTeamActiveStatusService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private LocalDateAndTimeService localDateAndTimeService;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private PermissionValidator permissionValidator;


    public ResponseMessage changeTeamActiveStatus() {
        UserAccount loggedUser = userAccountAuthenticatedService.get();

        permissionValidator.validateManagerPermission(loggedUser);

        Team team = loggedUser.getTeam();

        Boolean isTeamActive = team.getActive();

        team.setActive(!isTeamActive);
        team.setDateTimeUpdate(localDateAndTimeService.getLocalDateTime());

        teamRepository.save(team);


        return ResponseMessage.builder()
                .response("You've changed successfully")
                .build();
    }
}