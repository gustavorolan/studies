package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.DeactivateDriverResponse;
import br.com.cwi.crescer.melevaai.controller.response.DeactivatePassengerResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPersonStatusService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeactivatePassengerServiceTest {

  @InjectMocks
  private DeactivatePassengerService service;

  @Mock
  private PassengerRepository passengerRepository;

  @Mock
  private VerifyPersonStatusService verifyPersonStatusService;

  @Mock
  private PassengerFinderByIdService passengerFinderByIdService;

  @Captor
  private ArgumentCaptor<Passenger> passengerArgumentCaptor;

  @Test
  @DisplayName("Deve desativar passageiro com sucesso")
  public void deveDesativarPassageiroComSucesso() {

    Long passengerId = 1L;

    Passenger passenger = PassengerFactory.get();

    when(passengerFinderByIdService.findByIdWithException(passengerId))
        .thenReturn(passenger);

    DeactivatePassengerResponse serviceResponse = service.deactivatePassanger(passengerId);
    String expectedServiceResponse = "Passageiro "+ passenger.getNamePassenger() +" desativado com sucesso";

    verify(passengerRepository).save(passengerArgumentCaptor.capture());
    Passenger passengerUsedInTest = passengerArgumentCaptor.getValue();

    assertEquals(PersonStatus.INATIVO, passengerUsedInTest.getPersonStatus());
    assertEquals(expectedServiceResponse, serviceResponse.getMessage());
  }

}