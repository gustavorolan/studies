package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.controller.request.MoneyPrinterRequest;
import br.com.cwi.crescer.melevaai.controller.response.MoneyPrinterResponse;
import br.com.cwi.crescer.melevaai.service.MoneyPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class MoneyPrinterController {

    @Autowired
    private MoneyPrinterService moneyPrinterService;

    @PutMapping("/imprimirDinheiro")
    public MoneyPrinterResponse moneyPrinter(@Valid @RequestBody MoneyPrinterRequest request) {
        return moneyPrinterService.post(request);
    }

}
