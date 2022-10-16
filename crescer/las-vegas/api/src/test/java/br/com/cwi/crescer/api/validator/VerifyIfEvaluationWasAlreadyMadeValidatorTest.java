package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.EvaluationFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class VerifyIfEvaluationWasAlreadyMadeValidatorTest {
    @InjectMocks
    private VerifyIfEvaluationWasAlreadyMadeValidator verify;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Test
    @DisplayName("Verify if user already has evaluated some video")
    void verifyIfEvaluationWasAlreadyMade() {
        UserAccount userAccount = UserAccountFactory.get();
        Video video = VideoFactory.get();
        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        Mockito.when(evaluationRepository.findByVideoAndUserAccount(video,userAccount)).thenReturn(Optional.of(evaluation));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            verify.verifyIfEvaluationWasAlreadyMade(video,userAccount);
        });
        Mockito.verify(evaluationRepository).findByVideoAndUserAccount(video,userAccount);

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Evaluation already made\"", exception.getMessage());

    }
}