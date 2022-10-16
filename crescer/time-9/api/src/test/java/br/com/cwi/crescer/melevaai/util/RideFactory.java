package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.model.ride.RideStatus;
import br.com.cwi.crescer.melevaai.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class RideFactory {

  public static Ride get() {
    return getBuilder().build();
  }

  public static Ride.RideBuilder getBuilder() {
    return Ride.builder()
        .startX(1)
        .startY(1)
        .endX(5)
        .endY(5)
        .status(RideStatus.SOLICITADA)
        .startTime(LocalDateTime.now())
        .driver(DriverFactory.get())
        .passenger(PassengerFactory.get());
  }

}
