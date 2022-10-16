package br.com.cwi.crescer.melevaai.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestNewRideResponse {
    private Long rideId;
    private String driverName;
    private String vehicleName;
    private Integer estimateTime;
    private Long driverId;
}
