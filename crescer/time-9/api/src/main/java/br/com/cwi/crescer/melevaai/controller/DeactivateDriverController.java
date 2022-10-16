package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.DeactivateDriverResponse;
import br.com.cwi.crescer.melevaai.service.DeactivateDriverService;
import br.com.cwi.crescer.melevaai.service.DeactivatePassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi/driver/{driverId}/deactivate")
public class DeactivateDriverController {

  @Autowired
  private DeactivateDriverService deactivateDriverService;

  @PutMapping
  public DeactivateDriverResponse deactivateDriver(@PathVariable Long driverId) {
    return deactivateDriverService.deactivateDriver(driverId);
  }
}
