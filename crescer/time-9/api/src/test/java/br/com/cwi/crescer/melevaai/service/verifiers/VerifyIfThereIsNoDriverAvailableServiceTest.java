package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyIfThereIsNoDriverAvailableService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class VerifyIfThereIsNoDriverAvailableServiceTest {
    @InjectMocks
    private VerifyIfThereIsNoDriverAvailableService verifyIfThereIsNoDriverAvailableService;

    @Test
    @DisplayName("Verify if drivers List is empty, receive array that already has filtered drivers those are in ride")
    void throwAnErrorIfThereIsNoDriversAvailableTest()  {
        List<Driver> driverList=new ArrayList<Driver>();
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                verifyIfThereIsNoDriverAvailableService.throwAnErrorIfThereIsNoDriversAvailable(driverList);
            });

            Assertions.assertEquals("Não tem motorista disponivel", exception.getMessage());


    }
}