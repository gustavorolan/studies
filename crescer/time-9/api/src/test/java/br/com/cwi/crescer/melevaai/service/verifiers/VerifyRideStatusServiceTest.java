package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.util.RideFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VerifyRideStatusServiceTest {

  @InjectMocks
  private VerifyRideStatusService service;

  @Test
  @DisplayName("Deve retornar erro se a corrida nao esta solicitada")
  public void deveRetornarErroSeACorridaNaoEstaSolicitada() {
    Ride ride = RideFactory.get();
    ride.setStatus(RideStatus.INICIADA);

    Exception exception = assertThrows(ResponseStatusException.class, () ->
        service.ifRideIsNotSolicitadaThrowsAnException(ride));

    ResponseStatusException expectedResult = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "corrida nao esta solicitada");

    assertEquals(expectedResult.getMessage(), exception.getMessage());
  }

  @Test
  @DisplayName("Deve retornar erro se a corrida nao esta iniciada")
  public void deveRetornarErroSeACorridaNaoEstaIniciada() {
    Ride ride = RideFactory.get();
    ride.setStatus(RideStatus.SOLICITADA);

    Exception exception = assertThrows(ResponseStatusException.class, () ->
        service.ifRideIsNotIniciadaThrowsAnException(ride));

    ResponseStatusException expectedResult = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "corrida nao esta iniciada");

    assertEquals(expectedResult.getMessage(), exception.getMessage());
  }

}