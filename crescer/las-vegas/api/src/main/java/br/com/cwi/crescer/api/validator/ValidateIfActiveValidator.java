package br.com.cwi.crescer.api.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidateIfActiveValidator {
    public void validateIfActive(Boolean active) {
        if (!active) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email already in use");
        }
    }
}
