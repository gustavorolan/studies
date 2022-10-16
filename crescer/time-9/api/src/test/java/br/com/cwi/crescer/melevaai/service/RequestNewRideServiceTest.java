package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.RequestNewRideRequest;
import br.com.cwi.crescer.melevaai.controller.response.RequestNewRideResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.driver.Vehicle;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyIfThereIsNoDriverAvailableService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPassengerHasOpenRideService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestNewRideServiceTest {
    @InjectMocks
    private RequestNewRideService requestNewRideService;
    @Mock
    private VerifyPassengerHasOpenRideService verifyPassengerHasOpenRideService;
    @Mock
    private VerifyIfThereIsNoDriverAvailableService verifyIfThereIsNoDriverAvailableService;
    @Mock
    private DateTimeService dateTimeService;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private GetDriversThatAreAvailableService getDriversThatAreAvailableService;
    @Mock
    private PassengerFinderByIdService passengerFinderByIdService;
    @Mock
    private RideRepository rideRepository;
    @Captor
    private ArgumentCaptor<Ride> rideCaptor;
    @Captor
    private ArgumentCaptor<Driver> driverCaptor;

    @Test
    void createRideTest() {
        LocalDateTime randomDateAndTime = LocalDateTime
                .parse("2018-07-22 10:35:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Ride ride = new Ride();
        List <Ride> rideList = new ArrayList<Ride>();
        rideList.add(ride);

        Vehicle vehicle=new Vehicle();
        vehicle.setModel("Uno");

        Passenger passenger = Passenger.builder()
                .rides(rideList)
                .passengerId(1L)
                .build();

        Driver driverOne = DriverFactory.getBuilder()
                .score(5)
                .personStatus(PersonStatus.LIVRE)
                .vehicle(vehicle)
                .build();

        Driver driverTwo = DriverFactory.getBuilder()
                .score(4)
                .personStatus(PersonStatus.LIVRE)
                .build();

        Driver driverThree = DriverFactory.getBuilder()
                .personStatus(PersonStatus.OCUPADO)
                .score(5)
                .build();

        Driver driverFour = DriverFactory.getBuilder()
                .personStatus(PersonStatus.INATIVO)
                .score(5)
                .build();

        List<Driver> driverList = new ArrayList<Driver>();
        driverList.add(driverOne);
        driverList.add(driverTwo);
        driverList.add(driverThree);
        driverList.add(driverFour);

        RequestNewRideRequest request = new RequestNewRideRequest();
        request.setEndX(1);
        request.setEndY(2);
        request.setStartX(1);
        request.setStartY(2);
        request.setPassengerId(1L);

        List<Driver> driverListFiltered = new ArrayList<Driver>();
        driverListFiltered.add(driverOne);
        driverListFiltered.add(driverTwo);

        Ride rideExpected = new Ride();
        rideExpected.setDriver(driverOne);
        rideExpected.setPassenger(passenger);
        rideExpected.setStartX(request.getStartX());
        rideExpected.setStartY(request.getStartY());
        rideExpected.setEndX(request.getEndX());
        rideExpected.setEndY(request.getEndY());
        rideExpected.setStartTime(randomDateAndTime);
        rideExpected.setStatus(RideStatus.SOLICITADA);

        when(passengerFinderByIdService
                .findByIdWithException(request.getPassengerId()))
                .thenReturn(passenger);

        when(dateTimeService.dateAndTimeNow()).thenReturn(randomDateAndTime);

        when(driverRepository.driverFilterToStartRide(dateTimeService.dateNow()))
                .thenReturn(driverList);
        when(getDriversThatAreAvailableService.getDriversThatAreAvailable(driverList))
                .thenReturn(driverListFiltered);

        RequestNewRideResponse createResult = requestNewRideService.createRide(request);

        verify(passengerFinderByIdService).findByIdWithException(request.getPassengerId());
        verify(dateTimeService).dateAndTimeNow();
        verify(verifyPassengerHasOpenRideService).IfPassengerIsAlreadyInRideThrowsAnException(passenger);
        verify(driverRepository).driverFilterToStartRide(dateTimeService.dateNow());
        verify(getDriversThatAreAvailableService).getDriversThatAreAvailable(driverList);
        verify(verifyIfThereIsNoDriverAvailableService)
                .throwAnErrorIfThereIsNoDriversAvailable(driverListFiltered);

        verify(rideRepository).save(rideCaptor.capture());
        verify(driverRepository).save(driverCaptor.capture());

        Ride rideResult = rideCaptor.getValue();
        Driver driverResult = driverCaptor.getValue();


        assertEquals(rideResult.getRideId(),driverResult.getLastRideId());
        assertEquals(rideExpected,rideResult);
        assertTrue(createResult.getEstimateTime()>=0 &&
                createResult.getEstimateTime()<=5);
    }
}