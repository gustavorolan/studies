package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.MoneyWithdrawRequest;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyIfDrawIsBiggerThanBalanceService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoneyWithdrawServiceTest {
    @InjectMocks
    private MoneyWithdrawService moneyWithdrawService;
    @Mock
    private Validators validators;
    @Mock
    private DriverRepository driverRepository;
    @Captor
    private ArgumentCaptor<Driver> driverCaptor;
    @Mock
    private DriverFinderByIdService driverFinderByIdService;
    @Mock
    private VerifyIfDrawIsBiggerThanBalanceService verifyIfDrawIsBiggerThanBalanceService;
    @Test
    @DisplayName("Should withdraw some money on driver's account")
    void postTest() {
        BigDecimal balanceExpected = new BigDecimal(1000);
        BigDecimal driverBalance = new BigDecimal(3000);
        Driver driver = DriverFactory.getBuilder()
                .balance(new BigDecimal(3000))
                .build();
        MoneyWithdrawRequest request= new MoneyWithdrawRequest();
        request.setMoneyToDraw(new BigDecimal(2000));

        when(driverFinderByIdService.findByIdWithException(request.getDriverID()))
                .thenReturn(driver);

        moneyWithdrawService.post(request);

        verify(driverFinderByIdService).findByIdWithException(request.getDriverID());
        verify(validators).verifyIfMoneyInputIsBiggerThanZero(request.getMoneyToDraw());
        verify(verifyIfDrawIsBiggerThanBalanceService)
                .verifyIfDrawIsBiggerThanBalance(request.getMoneyToDraw(),driverBalance);

        verify(driverRepository).save(driverCaptor.capture());

        assertEquals(balanceExpected,driverCaptor.getValue().getBalance());
    }
}