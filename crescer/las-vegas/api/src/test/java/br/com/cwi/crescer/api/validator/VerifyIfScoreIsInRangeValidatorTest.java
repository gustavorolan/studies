package br.com.cwi.crescer.api.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerifyIfScoreIsInRangeValidatorTest {

    @InjectMocks
    private VerifyIfScoreIsInRangeValidator verifyIfScoreIsInRangeValidator;

    @Test
    @DisplayName("Verify if number is not in the range")
    void validateIfNotInRange() {
        int from = 0;
        int to = 5;
        int score = 8;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            verifyIfScoreIsInRangeValidator.verifyIfScoreIsInRange(score, from, to);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Score is invalid\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verify if number is in range")
    void validateIfActive() {
        int from = 0;
        int to = 5;
        int score = 3;

        verifyIfScoreIsInRangeValidator.verifyIfScoreIsInRange(score, from, to);
    }
}