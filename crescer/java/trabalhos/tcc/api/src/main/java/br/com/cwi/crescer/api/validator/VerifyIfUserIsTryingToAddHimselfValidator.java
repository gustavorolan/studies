package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerifyIfUserIsTryingToAddHimselfValidator {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    public void verifyIfUserIsTryingToAddHimself(Long id) {

        UserAccount userLogged = findUserAuthenticatedService.getUser();

        if (userLogged.getUserId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ta tentando se adicionar");
        }
    }
}
