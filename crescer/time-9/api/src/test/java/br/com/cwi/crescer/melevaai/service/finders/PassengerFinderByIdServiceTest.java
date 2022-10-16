package br.com.cwi.crescer.melevaai.service.finders;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class PassengerFinderByIdServiceTest {

  @InjectMocks
  private PassengerFinderByIdService service;

  @Mock
  private PassengerRepository passengerRepository;

  @Test
  @DisplayName("Deve buscar passageiro com sucesso")
  void deveBuscarPassageiroComSucesso() {
    Long passengerId = 1L;

    Passenger passenger = new Passenger();

    Mockito.when(passengerRepository.findById(passengerId)).thenReturn(java.util.Optional.of(passenger));

    service.findByIdWithException(passengerId);

    Mockito.verify(passengerRepository).findById(passengerId);
  }

  @Test
  @DisplayName("Deve retornar erro ao nao encontrar passageiro")
  void deveRetornarErroAoNaoEncontrarPassenger() {
    Long passengerId = 1L;

    Exception exception = Assertions.assertThrows(ResponseStatusException.class, () ->
        service.findByIdWithException(passengerId));

    ResponseStatusException expectedResult = new ResponseStatusException(HttpStatus.NOT_FOUND, "passageiro nao existe");

    Assertions.assertEquals(expectedResult.getMessage(), exception.getMessage());
  }
}
