package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestNewRideRequest {
    private int startX;

    private int startY;

    private int endX;

    private int endY;

    private Long passengerId;
}
