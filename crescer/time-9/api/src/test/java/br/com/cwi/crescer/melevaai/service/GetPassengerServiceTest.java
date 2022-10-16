package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetPassengerResponse;
import br.com.cwi.crescer.melevaai.mapper.GetPassengerMapper;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPassengerServiceTest {
    @InjectMocks
    private GetPassengerService getPassengerService;
    @Mock
    private PassengerFinderByIdService passengerFinderByIdService;

    @Mock
    private GetPassengerMapper getPassengerMapper;

    @Test
    @DisplayName("Should return a passenger response")
    void getPassengerTest() {
        Long passengerId = 1L;
        Passenger passenger = PassengerFactory.getBuilder().build();
        GetPassengerResponse passengerResponse = new GetPassengerResponse();
        passengerResponse.setNamePassenger("passageiro testes");
        passengerResponse.setBalance(new BigDecimal("20"));
        passengerResponse.setCpf("12345678900");
        passengerResponse.setPersonStatus(PersonStatus.LIVRE);
        passengerResponse.setScore(5);
        passengerResponse.setBirthDate(LocalDate.now());

        when(passengerFinderByIdService.findByIdWithException(passengerId))
                .thenReturn(passenger);

        when(getPassengerMapper.toResponse(passenger)).thenReturn(passengerResponse);

        getPassengerService.getPassenger(passengerId);

        verify(passengerFinderByIdService).findByIdWithException(passengerId);
        verify(getPassengerMapper).toResponse(passenger);
    }
}