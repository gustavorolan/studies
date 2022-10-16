package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.DriverAssessRequest;
import br.com.cwi.crescer.melevaai.controller.response.DriverAssessResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import br.com.cwi.crescer.melevaai.util.RideFactory;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverAssessServiceTest {
    @InjectMocks
    private DriverAssessService driverAssessService;

    @Mock
    private Validators validators;

    @Mock
    private RideFinderByIdService rideFinderByIdService;

    @Mock
    private RideRepository rideRepository;
    @Captor
    private ArgumentCaptor<Ride> rideCaptor;

    @Test
    @DisplayName("Should calculate the average score right and return right response string")
    void DriverAssessServicePutTest() {
        String expectedResponse = "Você avaliou o passageiro testes com a nota: 4";
        double scorePassengerExpected = 3.5;
        float scoreRequest = 4;
        Long idRide=1L;

        DriverAssessRequest request = new DriverAssessRequest();
        request.setRideId(idRide);
        request.setScore((int) scoreRequest);

        Passenger passenger = PassengerFactory.getBuilder().build();

        Ride ride = RideFactory.getBuilder()
                .rideId(idRide)
                .passenger(passenger)
                .passengerScore(4)
                .build();
        Ride rideTwo = RideFactory.getBuilder()
                .rideId(idRide)
                .passenger(passenger)
                .passengerScore(3)
                .build();

        Ride rideThree = RideFactory.getBuilder()
                .rideId(idRide)
                .passenger(passenger)
                .passengerScore(null)
                .build();


        List<Ride> rides = new ArrayList<Ride>();
        rides.add(ride);
        rides.add(rideTwo);
        rides.add(rideThree);
        passenger.setRides(rides);


        when(rideFinderByIdService.findByIdWithException(request.getRideId()))
                .thenReturn(ride);

        DriverAssessResponse result = driverAssessService.put(request);

        verify(rideRepository).save(rideCaptor.capture());
        verify(validators).verifyIfScoreIsBetweenZeroAndFive(request.getScore());
        verify(rideFinderByIdService).findByIdWithException(request.getRideId());

        Ride resultCaptorRide = rideCaptor.getValue();

        assertEquals(scorePassengerExpected,resultCaptorRide.getPassenger().getScore());
        assertEquals(scoreRequest,resultCaptorRide.getPassengerScore(),0);
        assertEquals(expectedResponse ,result.getResponse());
    }
}