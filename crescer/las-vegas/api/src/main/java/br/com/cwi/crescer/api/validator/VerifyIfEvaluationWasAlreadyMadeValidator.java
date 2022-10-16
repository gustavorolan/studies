package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerifyIfEvaluationWasAlreadyMadeValidator {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public void verifyIfEvaluationWasAlreadyMade(Video video, UserAccount userAccount) {
        if (evaluationRepository.findByVideoAndUserAccount(video, userAccount).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Evaluation already made");
        }
    }
}
