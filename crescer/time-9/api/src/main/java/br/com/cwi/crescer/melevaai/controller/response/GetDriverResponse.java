package br.com.cwi.crescer.melevaai.controller.response;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.DriverLicense;
import br.com.cwi.crescer.melevaai.model.driver.DriverStatusRide;
import br.com.cwi.crescer.melevaai.model.driver.Vehicle;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetDriverResponse {

  private Long driverId;
  private String nameDriver;
  private LocalDate birthDate;
  private String cpf;
  private DriverLicense driverLicense;
  private Vehicle vehicle;
  private BigDecimal balance;
  private float score;
  private PersonStatus personStatus;
  private List<Ride> rides;
  private DriverStatusRide driverStatusRide;
  private Long lastRideId;
}
