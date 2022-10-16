package br.com.cwi.crescer.melevaai.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FinishRideResponse {

  private BigDecimal totalPrice;
  private Integer totalTimeInSeconds;
}
