package br.com.cwi.crescer.melevaai.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StartRideResponse {

  private int estimatedArrivalTime;
  private BigDecimal estimatedPrice;
}
