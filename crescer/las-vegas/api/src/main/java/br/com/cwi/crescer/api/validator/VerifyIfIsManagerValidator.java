package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Permission;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerifyIfIsManagerValidator {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;
    public void verifyIsManager() {
        UserAccount loggedUser = userAccountAuthenticatedService.get();
        if (!(loggedUser.getPermission().equals(Permission.MANAGER))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized opperation");
        }
    }
}
