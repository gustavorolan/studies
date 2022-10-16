package br.com.cwi.crescer.api.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerifyIfScoreIsInRangeValidator {
    public void verifyIfScoreIsInRange(int score, int from, int to) {
        if (!(score >= from && score <= to)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Score is invalid");
        }
    }
}