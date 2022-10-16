package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.response.GetDriverResponse;
import br.com.cwi.crescer.melevaai.service.GetDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class GetDriverController {

  @Autowired
  private GetDriverService getDriverService;

  @GetMapping("/motoristas/{driverId}")
  public GetDriverResponse getDriver(@PathVariable Long driverId) {
    return getDriverService.getDriver(driverId);
  }
}
