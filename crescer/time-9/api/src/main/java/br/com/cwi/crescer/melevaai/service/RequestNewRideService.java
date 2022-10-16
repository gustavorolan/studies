package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.RequestNewRideRequest;

import br.com.cwi.crescer.melevaai.controller.response.RequestNewRideResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.driver.DriverStatusRide;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyIfThereIsNoDriverAvailableService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPassengerHasOpenRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestNewRideService {
    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private PassengerFinderByIdService passengerFinderByIdService;

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private VerifyPassengerHasOpenRideService verifyPassengerHasOpenRideService;

    @Autowired
    private GetDriversThatAreAvailableService getDriversThatAreAvailableService;

    @Autowired
    private VerifyIfThereIsNoDriverAvailableService verifyIfThereIsNoDriverAvailableService;

    public RequestNewRideResponse createRide(RequestNewRideRequest request) {
        Passenger passenger = passengerFinderByIdService
                .findByIdWithException(request.getPassengerId());

        Ride ride = new Ride();
        ride.setStartX(request.getStartX());
        ride.setStartY(request.getStartY());
        ride.setEndX(request.getEndX());
        ride.setEndY(request.getEndY());
        ride.setStartTime(dateTimeService.dateAndTimeNow());
        ride.setPassenger(passenger);

        verifyPassengerHasOpenRideService.IfPassengerIsAlreadyInRideThrowsAnException(passenger);

        List<Driver> drivers = driverRepository.driverFilterToStartRide(dateTimeService.dateNow());

        List<Driver> driverFilteredByAvailability = getDriversThatAreAvailableService.getDriversThatAreAvailable(drivers);

        verifyIfThereIsNoDriverAvailableService.throwAnErrorIfThereIsNoDriversAvailable(driverFilteredByAvailability);

        ride.setStatus(RideStatus.SOLICITADA);

        Driver selectedDriver = driverFilteredByAvailability.get(0);

        ride.setDriver(selectedDriver);

        passenger.getRides().add(ride);

        passenger.setPersonStatus(PersonStatus.OCUPADO);
        selectedDriver.setPersonStatus(PersonStatus.OCUPADO);
        selectedDriver.setDriverStatusRide(DriverStatusRide.ACEITAR);

        rideRepository.save(ride);
        
        selectedDriver.setLastRideId(ride.getRideId());
        driverRepository.save(selectedDriver);

        RequestNewRideResponse response = new RequestNewRideResponse();
        response.setRideId(ride.getRideId());
        response.setDriverName(selectedDriver.getNameDriver());
        response.setVehicleName(selectedDriver.getVehicle().getModel());
        response.setEstimateTime((int) Math.round(Math.random()*5));
        response.setDriverId(selectedDriver.getDriverId());
        return response;
    }
}

