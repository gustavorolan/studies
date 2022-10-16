package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPassengerHasOpenRideService;
import br.com.cwi.crescer.melevaai.util.RideFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class VerifyPassengerHasOpenRideServiceTest {
    @InjectMocks
    private VerifyPassengerHasOpenRideService verifyPassengerHasOpenRideService;

    @Test
    @DisplayName("Should throw an exception passenger has an open ride")
    void ifPassengerIsAlreadyInRideThrowsAnExceptionTest() {
        Ride ride = RideFactory.getBuilder().build();
        List<Ride> rides = new ArrayList<Ride>();
        rides.add(ride);
        Passenger passenger = Passenger.builder().rides(rides).build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            verifyPassengerHasOpenRideService.IfPassengerIsAlreadyInRideThrowsAnException(passenger);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Passageiro tem uma corrida em aberto\"", exception.getMessage());

    }
}