package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DriverAssessRequest {
    private Integer score;
    private Long rideId;
}
