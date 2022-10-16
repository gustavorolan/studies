package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.MoneyPrinterRequest;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
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
class MoneyPrinterServiceTest {
    @InjectMocks
    private  MoneyPrinterService moneyPrinterService;
    @Mock
    private Validators validators;
    @Mock
    private PassengerFinderByIdService passengerFinderByIdService;
    @Mock
    private PassengerRepository passengerRepository;

    @Captor
    private ArgumentCaptor<Passenger> passengerCaptor;

    @Test
    @DisplayName("Should put some money on passenger's account")
    void postTest() {
        String expectedString = "Você depositou 100 na sua conta! Agora você tem: 2100";
        BigDecimal balanceExpected = new BigDecimal(2100);
        MoneyPrinterRequest request = new MoneyPrinterRequest();
        request.setMoneyToAdd(new BigDecimal(100));
        request.setPassengerId(2L);
        Passenger passenger= PassengerFactory.getBuilder()
                .passengerId(2L)
                .balance(new BigDecimal(2000))
                .build();

        when(passengerFinderByIdService.findByIdWithException(request.getPassengerId()))
                .thenReturn(passenger);

        when(passengerFinderByIdService.findByIdWithException(request.getPassengerId()))
                .thenReturn(passenger);

        moneyPrinterService.post(request);

        verify(passengerFinderByIdService).findByIdWithException(request.getPassengerId());
        verify(validators).verifyIfMoneyInputIsBiggerThanZero(request.getMoneyToAdd());
        verify(passengerRepository).save(passengerCaptor.capture());
        Passenger result =passengerCaptor.getValue();
        assertEquals(balanceExpected,result.getBalance());
    }
}