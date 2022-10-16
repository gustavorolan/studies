package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.controller.request.DriverAssessRequest;
import br.com.cwi.crescer.melevaai.controller.request.MoneyPrinterRequest;
import br.com.cwi.crescer.melevaai.controller.response.DriverAssessResponse;
import br.com.cwi.crescer.melevaai.controller.response.MoneyPrinterResponse;
import br.com.cwi.crescer.melevaai.service.DriverAssessService;
import br.com.cwi.crescer.melevaai.service.MoneyPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class DriverAssessController {

    @Autowired
    private DriverAssessService driverAssessService;

    @PutMapping("/driverAvalia")
    public DriverAssessResponse moneyPrinter(@Valid @RequestBody DriverAssessRequest request) {
        return driverAssessService.put(request);
    }

}
