package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.StartRideResponse;
import br.com.cwi.crescer.melevaai.model.driver.DriverStatusRide;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyRideStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StartRideService {

  @Autowired
  private RideRepository rideRepository;

  @Autowired
  private RideFinderByIdService rideFinderByIdService;

  @Autowired
  private VerifyRideStatusService verifyRideStatusService;

  @Autowired
  private DateTimeService dateTimeService;

  @Autowired
  private StartRideUtilsService startRideUtilsService;

  public StartRideResponse startRide(Long rideId) {
    Ride ride = rideFinderByIdService.findByIdWithException(rideId);

    verifyRideStatusService.ifRideIsNotSolicitadaThrowsAnException(ride);

    LocalDateTime startDate = dateTimeService.dateAndTimeNow();

    ride.setStartTime(startDate);
    ride.setStatus(RideStatus.INICIADA);

    ride.getDriver().setDriverStatusRide(DriverStatusRide.FINALIZAR);

    rideRepository.save(ride);

    double distance = startRideUtilsService.getRideDistance(ride.getEndX(), ride.getStartX(), ride.getEndY(), ride.getStartY());

    int estimatedArrivalTime = startRideUtilsService.getEstimatedArrivalTime(distance);

    BigDecimal estimatedPrice = startRideUtilsService.getEstimatedPrice(estimatedArrivalTime);

    StartRideResponse response = new StartRideResponse();
    response.setEstimatedArrivalTime(estimatedArrivalTime);
    response.setEstimatedPrice(estimatedPrice);

    return response;
  }
}
