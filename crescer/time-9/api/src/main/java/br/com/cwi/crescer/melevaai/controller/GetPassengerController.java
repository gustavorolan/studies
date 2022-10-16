package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.GetPassengerResponse;
import br.com.cwi.crescer.melevaai.service.GetPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class GetPassengerController {

  @Autowired
  private GetPassengerService getPassengerService;

  @GetMapping("/passageiros/{passengerId}")
  public GetPassengerResponse getPassenger(@PathVariable Long passengerId) {
    return getPassengerService.getPassenger(passengerId);
  }
}
