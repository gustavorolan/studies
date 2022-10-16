package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@Component
public class IsUserLoggedValidator {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public void verify (UserAccount author)   {
    if(!userAccountAuthenticatedService.get().equals(author))
        throw new ResponseStatusException(BAD_REQUEST, "User is not logged");
    }
}
