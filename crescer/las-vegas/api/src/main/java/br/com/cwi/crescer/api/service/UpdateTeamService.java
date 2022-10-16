package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.UpdateTeamRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.TeamRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private VerifyImageParameterService verifyImageParameterService;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;


    public ResponseMessage update(UpdateTeamRequest request, String imageId) {
        UserAccount loggedUser = userAccountAuthenticatedService.get();

        parametersRegexValidator.verifyIfImageIdIsValid(imageId);

        permissionValidator.validateManagerPermission(loggedUser);

        Team team = loggedUser.getTeam();

        Attachment attachment = verifyImageParameterService.verifyImageParameter(imageId);

        team.setName(request.getName().isBlank() ? team.getName() : request.getName());
        team.setDescription(request.getDescription().isBlank() ? team.getDescription() : request.getDescription());
        team.setImage( attachment==null ? team.getImage() : attachment);

        teamRepository.save(team);

        return ResponseMessage.builder().response("You've updated successfully").build();

    }
}
