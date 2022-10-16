package br.com.cwi.crescer.melevaai.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ValidatorsTest {
    @InjectMocks
    private Validators validators;

    @Test
    @DisplayName("Should throw an error if money value is smaller than 0")
    void verifyIfMoneyInputIsSmallerThanZeroTest() {
        BigDecimal moneySmallerThanZero = new BigDecimal((-2000));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            validators.verifyIfMoneyInputIsBiggerThanZero(moneySmallerThanZero);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Valor deve ser maior que zero\"", exception.getMessage());
    }



    @Test
    @DisplayName("Should throw an error if score input is bigger than 5")
    void verifyIfScoreIsBetweenZeroAndFiveBiggerTest() {
        float scoreInput=8;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            validators.verifyIfScoreIsBetweenZeroAndFive(scoreInput);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Nota deve ser entre 0 e 5\"", exception.getMessage());

    }

    @Test
    @DisplayName("Should throw an error if score input is smaller than 5")
    void verifyIfScoreIsBetweenZeroAndFiveSmallerTest() {
        float scoreInput=8;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            validators.verifyIfScoreIsBetweenZeroAndFive(scoreInput);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Nota deve ser entre 0 e 5\"", exception.getMessage());

    }
}