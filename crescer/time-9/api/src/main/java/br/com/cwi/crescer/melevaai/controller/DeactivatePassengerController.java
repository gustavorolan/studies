package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.DeactivatePassengerResponse;
import br.com.cwi.crescer.melevaai.service.DeactivatePassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi/passenger/{passengerId}/deactivate")
public class DeactivatePassengerController {

  @Autowired
  private DeactivatePassengerService deactivatePassengerService;

  @PutMapping
  public DeactivatePassengerResponse deactivateDriver(@PathVariable Long passengerId) {
    return deactivatePassengerService.deactivatePassanger(passengerId);
  }
}
