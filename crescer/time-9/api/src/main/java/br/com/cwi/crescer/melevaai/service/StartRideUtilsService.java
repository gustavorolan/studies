package br.com.cwi.crescer.melevaai.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StartRideUtilsService {

  public double getRideDistance(Integer endX, Integer startX, Integer endY, Integer startY) {
    return Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
  }

  public int getEstimatedArrivalTime(double distance) {
    double speed = 30;

    double timeInHours = distance / speed;

    double timeInSeconds = timeInHours * 60 * 60;

    return (int)Math.round(timeInSeconds);
  }

  public BigDecimal getEstimatedPrice(int estimatedTime) {
    BigDecimal pricePerSecond = new BigDecimal("0.2");
    return pricePerSecond.multiply(new BigDecimal(estimatedTime));
  }
}
