package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Permission;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PermissionValidator {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    private static final String UNAUTHORIZED = "Unauthorized";
    public Boolean validateManagerPermission(UserAccount loggedUser) {
        if (!loggedUser.getPermission().equals(Permission.MANAGER)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
        }
        return true;
    }

    public void validateLoggedUserPermission(UserAccount user) {
        UserAccount loggedUser = userAccountAuthenticatedService.get();
        if (!validateManagerPermission(loggedUser) || !user.equals(loggedUser)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
        }
    }
}