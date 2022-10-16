package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.controller.request.MoneyPrinterRequest;
import br.com.cwi.crescer.melevaai.controller.request.MoneyWithdrawRequest;
import br.com.cwi.crescer.melevaai.controller.response.MoneyPrinterResponse;
import br.com.cwi.crescer.melevaai.controller.response.MoneyWithdrawResponse;
import br.com.cwi.crescer.melevaai.service.MoneyPrinterService;
import br.com.cwi.crescer.melevaai.service.MoneyWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class MoneyWithdrawController {

    @Autowired
    private MoneyWithdrawService moneyWithdrawService;

    @PutMapping("/retirarDinheiro")
    public MoneyWithdrawResponse moneyPrinter(@Valid @RequestBody MoneyWithdrawRequest request) {
        return moneyWithdrawService.post(request);
    }

}
