package br.com.cwi.crescer.api.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateIfActiveValidatorTest {

    @InjectMocks
    private ValidateIfActiveValidator validateIfActiveValidator;

    @Test
    @DisplayName("Validate if it's active ")
    void validateIfActive() {
        boolean input = false;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            validateIfActiveValidator.validateIfActive(input);
        });


        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Email already in use\"", exception.getMessage());

    }
}