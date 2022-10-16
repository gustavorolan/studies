package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserEmailValidator {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void validateIfUserEmailIsInUse(String email) {
        if (userAccountRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email already in use");
        }
    }
}
