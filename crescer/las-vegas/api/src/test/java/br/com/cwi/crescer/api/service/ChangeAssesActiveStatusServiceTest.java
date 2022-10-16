package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.service.finder.FindEvaluationWithThrow;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.util.EvaluationFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ChangeAssesActiveStatusServiceTest {
    @InjectMocks
    private ChangeAssesActiveStatusService changeAssesActiveStatusService;

    @Mock
    private FindEvaluationWithThrow findEvaluationWithThrow;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private LocalDateAndTimeService localDateAndTimeService;

    @Captor
    private ArgumentCaptor<Evaluation> evaluationArgumentCaptor;

    @Test
    @DisplayName("Change asses active status")
    void changeAssesActiveStatus() {
        Long id = 1L;
        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();
        Evaluation evaluation = EvaluationFactory.getBuilder().active(true).build();
        Evaluation expected = EvaluationFactory.getBuilder().active(false).build();

        Mockito.when(findEvaluationWithThrow.findByIdWithException(id, true)).thenReturn(evaluation);
        Mockito.when(localDateAndTimeService.getLocalDateTime()).thenReturn(dateTime);

        changeAssesActiveStatusService.changeAssesActiveStatus(id);

        Mockito.verify(permissionValidator).validateLoggedUserPermission(evaluation.getUserAccount());
        Mockito.verify(findEvaluationWithThrow).findByIdWithException(id, true);
        Mockito.verify(localDateAndTimeService).getLocalDateTime();
        Mockito.verify(evaluationRepository).save(evaluationArgumentCaptor.capture());

        Assertions.assertEquals(expected,evaluationArgumentCaptor.getValue());


    }
}