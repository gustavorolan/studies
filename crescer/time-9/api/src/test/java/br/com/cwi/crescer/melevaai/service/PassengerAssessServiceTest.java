package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.PassengerAssessRequest;
import br.com.cwi.crescer.melevaai.controller.response.PassengerAssessResponse;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
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
class PassengerAssessServiceTest {
    @InjectMocks
    private PassengerAssessService passengerAssessService;

    @Mock
    private Validators validators;

    @Mock
    private RideFinderByIdService rideFinderByIdService;

    @Mock
    private RideRepository rideRepository;

    @Captor
    private ArgumentCaptor<Ride> rideCaptor;

    @DisplayName("Should test if driver assess is woking correctly")
    @Test
    void put() {
        String expectedResponse = "Você avaliou o driver testes com a nota: 4";
        double scoreDriverExpected = 3.5;
        float scoreRequest = 4;
        Long idRide=1L;

        PassengerAssessRequest request = new PassengerAssessRequest();
        request.setRideId(idRide);
        request.setScore((int) scoreRequest);

        Driver driver = DriverFactory.getBuilder().build();

        Ride ride = RideFactory.getBuilder()
                .rideId(idRide)
                .driver(driver)
                .passengerScore(4)
                .build();

        Ride rideTwo = RideFactory.getBuilder()
                .rideId(idRide)
                .driver(driver)
                .passengerScore(3)
                .build();

        Ride rideThree = RideFactory.getBuilder()
                .rideId(idRide)
                .driver(driver)
                .passengerScore(null)
                .build();


        List<Ride> rides = new ArrayList<Ride>();
        rides.add(ride);
        rides.add(rideTwo);
        rides.add(rideThree);
        driver.setRides(rides);


        when(rideFinderByIdService.findByIdWithException(request.getRideId()))
                .thenReturn(ride);

        PassengerAssessResponse result = passengerAssessService.put(request);

        verify(rideRepository).save(rideCaptor.capture());
        verify(validators).verifyIfScoreIsBetweenZeroAndFive(request.getScore());
        verify(rideFinderByIdService).findByIdWithException(request.getRideId());

        Ride resultCaptorRide = rideCaptor.getValue();

        assertEquals(scoreDriverExpected,resultCaptorRide.getDriver().getScore());
        assertEquals(scoreRequest,resultCaptorRide.getDriverScore(),0);
        assertEquals(expectedResponse ,result.getResponse());
    }
}