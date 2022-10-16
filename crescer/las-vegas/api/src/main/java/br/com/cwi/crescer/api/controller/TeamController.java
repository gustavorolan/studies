package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.TeamRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.controller.response.TeamResponse;
import br.com.cwi.crescer.api.controller.response.TeamCompleteResponse;
import br.com.cwi.crescer.api.controller.request.UpdateTeamRequest;
import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private IncludeTeamService includeTeamService;

    @Autowired
    private IncludeUserInTeamService includeUserInTeamService;

    @Autowired
    private ChangeTeamActiveStatusService changeTeamActiveStatusService;

    @Autowired
    private UpdateTeamService updateTeamService;

    @Autowired
    private GetDetailedTeamService getDetailedTeamService;

    @Autowired
    private GetAllUsersNotInTeamService getAllUsersNotInTeamService;

    @Autowired
    private GetAllUsersInTeamService getAllUsersInTeamService;

    @Autowired
    private RemoveUserFromTeamService removeUserFromTeamService;

    @Autowired
    private SupportMaterialService supportMaterialService;

    @GetMapping("/detail")
    public TeamCompleteResponse getDetailedTeam() {
        return getDetailedTeamService.getDetailedTeam();
    }

    @GetMapping("/allUsersNotInTeam")
    public Page<UserResponse> getAllUsersNotInTeam(Pageable pageable) {
        return getAllUsersNotInTeamService.getAllUsersNotInTeam(pageable);
    }

    @GetMapping("/allUsersInTeam")
    public List<UserResponse> getAllUsersInTeam() {
        return getAllUsersInTeamService.getAllUsersInTeam();
    }

    @PostMapping("/include/{imageId}")
    public TeamResponse includeTeam(@Valid @RequestBody TeamRequest request, @PathVariable String imageId) {
        return includeTeamService.includeTeam(request, imageId);
    }

    @PostMapping("/includeUserInTeam/{idUser}")
    public ResponseMessage includeUserInTeam(@PathVariable Long idUser) {
        return includeUserInTeamService.includeUserInTeam(idUser);
    }

    @PostMapping("/removeUserFromTeam/{idUser}")
    public UserResponse removeUserFromTeam(@PathVariable Long idUser) {
        return removeUserFromTeamService.removeUserFromTeam(idUser);
    }

    @PostMapping("/changeActiveStatus")
    public ResponseMessage changeTeamActiveStatus() {
        return changeTeamActiveStatusService.changeTeamActiveStatus();
    }

    @PostMapping("/update/{imageId}")
    public ResponseMessage update(@Valid @RequestBody UpdateTeamRequest request, @PathVariable String imageId) {
        return updateTeamService.update(request, imageId);
    }

}
