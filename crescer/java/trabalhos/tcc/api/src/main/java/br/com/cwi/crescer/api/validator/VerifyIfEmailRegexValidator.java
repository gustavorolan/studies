package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Friendship;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class VerifyIfEmailRegexValidator {
    public void verifyIfUserIfEmailIsValid(String email) {
        Pattern patternEmail= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
                ,Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmail.matcher(email);
        if (!matcher.find()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não é valido");
        }
    }
}
