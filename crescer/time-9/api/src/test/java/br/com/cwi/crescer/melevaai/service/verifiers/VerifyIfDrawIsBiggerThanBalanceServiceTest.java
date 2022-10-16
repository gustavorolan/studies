package br.com.cwi.crescer.melevaai.service.verifiers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith({MockitoExtension.class})
class VerifyIfDrawIsBiggerThanBalanceServiceTest {
    @InjectMocks
    private VerifyIfDrawIsBiggerThanBalanceService verifyIfDrawIsBiggerThanBalanceService;

    @Test
    @DisplayName("Should throw an error if drawInput is bigger than driverMoney")
    void verifyIfDrawIsBiggerThanBalanceTest() {
        BigDecimal drawInput = new BigDecimal((3000));
        BigDecimal driverMoney = new BigDecimal((2000));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            verifyIfDrawIsBiggerThanBalanceService
                    .verifyIfDrawIsBiggerThanBalance(drawInput,driverMoney);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Voce não pode retirar mais dinheiro do que você tem\"", exception.getMessage());
    }

}