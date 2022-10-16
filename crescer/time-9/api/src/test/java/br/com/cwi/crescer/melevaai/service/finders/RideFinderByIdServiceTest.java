package br.com.cwi.crescer.melevaai.service.finders;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
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
public class RideFinderByIdServiceTest {

  @InjectMocks
  private RideFinderByIdService service;

  @Mock
  private RideRepository rideRepository;

  @Test
  @DisplayName("Deve buscar passageiro com sucesso")
  void deveBuscarRideComSucesso() {
    Long rideId = 1L;

    Ride ride = new Ride();

    Mockito.when(rideRepository.findById(rideId)).thenReturn(java.util.Optional.of(ride));

    service.findByIdWithException(rideId);

    Mockito.verify(rideRepository).findById(rideId);
  }

  @Test
  @DisplayName("Deve retornar erro ao nao encontrar passageiro")
  void deveRetornarErroAoNaoEncontrarPassenger() {
    Long rideId = 1L;

    Exception exception = Assertions.assertThrows(ResponseStatusException.class, () ->
        service.findByIdWithException(rideId));

    ResponseStatusException expectedResult = new ResponseStatusException(HttpStatus.NOT_FOUND, "corrida nao existe");

    Assertions.assertEquals(expectedResult.getMessage(), exception.getMessage());
  }
}
