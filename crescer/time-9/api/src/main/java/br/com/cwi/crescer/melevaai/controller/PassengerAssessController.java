package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.controller.request.DriverAssessRequest;
import br.com.cwi.crescer.melevaai.controller.request.PassengerAssessRequest;
import br.com.cwi.crescer.melevaai.controller.response.DriverAssessResponse;
import br.com.cwi.crescer.melevaai.controller.response.PassengerAssessResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.service.DriverAssessService;
import br.com.cwi.crescer.melevaai.service.PassengerAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class PassengerAssessController {

    @Autowired
    private PassengerAssessService passengerAssessService;

    @PutMapping("/passengerAvalia")
    public PassengerAssessResponse moneyPrinter(@Valid @RequestBody PassengerAssessRequest request) {
        return passengerAssessService.put(request);
    }

}
