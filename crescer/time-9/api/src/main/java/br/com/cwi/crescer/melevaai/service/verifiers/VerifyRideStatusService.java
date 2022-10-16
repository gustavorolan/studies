package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VerifyRideStatusService {

  public void ifRideIsNotSolicitadaThrowsAnException(Ride ride) {
    boolean isSolicitada = (ride.getStatus() == RideStatus.SOLICITADA);

    if(!isSolicitada) {
      throw new ResponseStatusException(NOT_FOUND, "corrida nao esta solicitada");
    }
  }

  public void ifRideIsNotIniciadaThrowsAnException(Ride ride) {
    boolean isNotIniciada = (ride.getStatus() != RideStatus.INICIADA);

    if(isNotIniciada) {
      throw new ResponseStatusException(NOT_FOUND, "corrida nao esta iniciada");
    }
  }
}
