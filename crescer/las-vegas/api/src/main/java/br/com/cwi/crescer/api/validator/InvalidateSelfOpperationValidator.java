package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class InvalidateSelfOpperationValidator {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public void invalidateSelfOpperation(UserAccount userToAdd) {
        if (userAccountAuthenticatedService.get().equals(userToAdd)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid opperation");
        }
    }
}
