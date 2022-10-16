package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.validator.InvalidateSelfOpperationValidator;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveUserFromTeamService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTeamConstraintService userTeamConstraintService;

    @Autowired
    private InvalidateSelfOpperationValidator invalidateSelfOpperationValidator;

    @Autowired
    private FindUserWithThrow findUserWithThrow;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private PermissionValidator permissionValidator;

    public UserResponse removeUserFromTeam(Long idUser) {
        UserAccount adm = userAccountAuthenticatedService.get();
        permissionValidator.validateLoggedUserPermission(adm);

        UserAccount userAccount = findUserWithThrow.findByIdAndActiveWithException(idUser, true);

        invalidateSelfOpperationValidator.invalidateSelfOpperation(userAccount);

        Team team = adm.getTeam();

        userTeamConstraintService.verifyIfUserIsNotInTeam(team, userAccount);

        team.getUserAccountList().remove(userAccount);
        userAccount.setTeam(null);

        userAccountRepository.save(userAccount);

        return userMapper.toResponse(userAccount);
    }
}
