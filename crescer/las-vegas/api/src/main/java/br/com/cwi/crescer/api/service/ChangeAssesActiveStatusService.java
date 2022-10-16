package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.service.finder.FindEvaluationWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeAssesActiveStatusService {
    @Autowired
    private FindEvaluationWithThrow findEvaluationWithThrow;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private LocalDateAndTimeService localDateAndTimeService;

    public void changeAssesActiveStatus(Long evaluationId) {

        Evaluation evaluation = findEvaluationWithThrow.findByIdWithException(evaluationId, true);

        permissionValidator.validateLoggedUserPermission(evaluation.getUserAccount());

        Boolean isEvaluationActive = evaluation.getActive();

        evaluation.setActive(!isEvaluationActive);
        evaluation.setDateTimeUpdate(localDateAndTimeService.getLocalDateTime());

        evaluationRepository.save(evaluation);
    }
}
