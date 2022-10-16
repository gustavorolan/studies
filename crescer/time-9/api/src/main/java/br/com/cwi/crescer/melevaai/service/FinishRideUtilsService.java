package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class FinishRideUtilsService {

  public BigDecimal getRidePrice(int timeInSeconds) {
    BigDecimal pricePerSecond = new BigDecimal("0.2");
    return pricePerSecond.multiply(new BigDecimal(timeInSeconds));
  }

  public int getRideTimeInSeconds(LocalDateTime startTime, LocalDateTime todayTimeDate) {
    return (int) startTime.until(todayTimeDate, ChronoUnit.SECONDS);
  }
}
