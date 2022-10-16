package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class VerifyPassengerHasOpenRideService {
    public void IfPassengerIsAlreadyInRideThrowsAnException(Passenger passenger){
        boolean itHasOpenRide = passenger.getRides().stream().anyMatch((ride) -> {
            return ride.getStatus().equals(RideStatus.INICIADA) || ride.getStatus().equals(RideStatus.SOLICITADA);
        });

        if(itHasOpenRide) {
            throw new ResponseStatusException(BAD_REQUEST,"Passageiro tem uma corrida em aberto");
        }
    }

}
