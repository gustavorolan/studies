package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.FinishRideResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import br.com.cwi.crescer.melevaai.service.finders.RideFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPassangerBalanceService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyRideStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class FinishRideService {

  @Autowired
  private RideRepository rideRepository;

  @Autowired
  private RideFinderByIdService rideFinderByIdService;

  @Autowired
  private VerifyRideStatusService verifyRideStatusService;

  @Autowired
  private VerifyPassangerBalanceService verifyPassangerBalanceService;

  @Autowired
  private FinishRideUtilsService finishRideUtilsService;

  @Autowired
  private DateTimeService dateTimeService;

  public FinishRideResponse finishRide(Long rideId) {
    Ride ride = rideFinderByIdService.findByIdWithException(rideId);

    verifyRideStatusService.ifRideIsNotIniciadaThrowsAnException(ride);

    LocalDateTime todayFinishTimeDate = dateTimeService.dateAndTimeNow();

    int timeInSeconds = finishRideUtilsService
        .getRideTimeInSeconds(ride.getStartTime(), todayFinishTimeDate);

    BigDecimal totalPrice = finishRideUtilsService.getRidePrice(timeInSeconds);

    Passenger passenger = ride.getPassenger();
    Driver driver = ride.getDriver();

    verifyPassangerBalanceService.verifiyBalance(passenger, totalPrice);

    ride.setFinishTime(todayFinishTimeDate);
    ride.setStatus(RideStatus.FINALIZADA);

    BigDecimal newPassengerBalance = passenger.getBalance().subtract(totalPrice);
    BigDecimal newDriverBalance = driver.getBalance().add(totalPrice);

    passenger.setBalance(newPassengerBalance);
    passenger.setPersonStatus(PersonStatus.LIVRE);
    driver.setBalance(newDriverBalance);
    driver.setPersonStatus(PersonStatus.LIVRE);
    driver.setDriverStatusRide(null);

    rideRepository.save(ride);

    FinishRideResponse response = new FinishRideResponse();
    response.setTotalPrice(totalPrice);
    response.setTotalTimeInSeconds(timeInSeconds);

    return response;
  }
}
