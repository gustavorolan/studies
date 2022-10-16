package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.MoneyWithdrawRequest;
import br.com.cwi.crescer.melevaai.controller.response.MoneyWithdrawResponse;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyIfDrawIsBiggerThanBalanceService;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyWithdrawService {
    @Autowired
    private DriverFinderByIdService driverFinderByIdService;

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private  Validators validators;
    @Autowired
    private VerifyIfDrawIsBiggerThanBalanceService verifyIfDrawIsBiggerThanBalanceService;

    public MoneyWithdrawResponse post(MoneyWithdrawRequest request) {
       Driver driver = driverFinderByIdService.findByIdWithException(request.getDriverID());
       validators.verifyIfMoneyInputIsBiggerThanZero(request.getMoneyToDraw());
       verifyIfDrawIsBiggerThanBalanceService.verifyIfDrawIsBiggerThanBalance(request.getMoneyToDraw(),driver.getBalance());
       driver.setBalance(driver.getBalance().subtract(request.getMoneyToDraw()));
       driverRepository.save(driver);
       MoneyWithdrawResponse response = new MoneyWithdrawResponse();
       response.setWithDrawResponse("Você retirou: "+request.getMoneyToDraw()+", você tem: "+driver.getBalance());
       return response;
    }
}
