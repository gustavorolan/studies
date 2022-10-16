package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.FinishRideResponse;
import br.com.cwi.crescer.melevaai.service.FinishRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi/{rideId}/finishRide")
public class FinishRideController {

  @Autowired
  private FinishRideService finishRideService;

  @PutMapping
  public FinishRideResponse finishRide(@PathVariable Long rideId) {
    return finishRideService.finishRide(rideId);
  }
}
