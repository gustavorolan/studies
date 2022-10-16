package br.com.cwi.crescer.api.service.verifys;

import br.com.cwi.crescer.api.model.UserAccount;
import br.com.cwi.crescer.api.service.security.FindUserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VerifyIfAccountLoggedIsRequestFriendAccountService {
    @Autowired
    private FindUserAuthenticatedService findUserAuthenticatedService;
    public void VerifyIfIsSameLogged(UserAccount userRequest) {

        UserAccount userLogged = findUserAuthenticatedService.getUser();

        if (!userLogged.equals(userRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user nao esta logado");
        }
    }
}
