package br.com.cwi.crescer.melevaai.controller.response;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.DriverStatusRide;
import br.com.cwi.crescer.melevaai.model.driver.Vehicle;
import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class GetAllDriversResponse {

    private Long driverId;

    private String nameDriver;

    private Vehicle vehicle;

    private PersonStatus personStatus;

    private DriverStatusRide driverStatusRide;

    private Long lastRideId;

    private BigDecimal balance;

    private List<Ride> rides;
}
