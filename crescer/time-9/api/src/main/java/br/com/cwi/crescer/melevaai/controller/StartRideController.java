package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.StartRideResponse;
import br.com.cwi.crescer.melevaai.service.StartRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi/{rideId}/startRide")
public class StartRideController {

  @Autowired
  private StartRideService startRideService;

  @PutMapping
  public StartRideResponse startRide(@PathVariable Long rideId) {
    return startRideService.startRide(rideId);
  }
}
