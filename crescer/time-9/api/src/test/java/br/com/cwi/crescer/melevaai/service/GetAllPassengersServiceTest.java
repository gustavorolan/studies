package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.PassengerResponse;
import br.com.cwi.crescer.melevaai.mapper.GetAllPassengersMapper;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllPassengersServiceTest {
    @InjectMocks
    private  GetAllPassengersService getAllPassengersService;
    @Mock
    private GetAllPassengersMapper getAllPassengersMapper;
    @Mock
    private PassengerRepository passengerRepository;

    @Test
    @DisplayName("Should return an driver response when interact with a passenger list")
    void getTest() {
        Passenger passenger = PassengerFactory.getBuilder().build();
        List<Passenger> passengerList = new ArrayList<Passenger>();
        passengerList.add(passenger);

        PassengerResponse passengerResponse = new PassengerResponse();
        passengerResponse.setNamePassenger("passageiro testes");
        passengerResponse.setBalance(new BigDecimal("20"));
        passengerResponse.setCpf("12345678900");
        passengerResponse.setPersonStatus(PersonStatus.LIVRE);
        passengerResponse.setScore(5);
        passengerResponse.setBirthDate(LocalDate.now());

        List<PassengerResponse> passengerResponseList = new ArrayList<PassengerResponse>();
        passengerResponseList.add(passengerResponse);

        when(passengerRepository.findAll())
                .thenReturn(passengerList);


        when(getAllPassengersMapper.toResponse(passenger))
                .thenReturn(passengerResponse);


        List<PassengerResponse> result = getAllPassengersService.get();

        verify(getAllPassengersMapper).toResponse(passenger);
        verify(passengerRepository).findAll();

        assertEquals(passengerResponseList,result);

    }
}