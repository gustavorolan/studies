package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.MoneyPrinterRequest;
import br.com.cwi.crescer.melevaai.controller.response.MoneyPrinterResponse;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import br.com.cwi.crescer.melevaai.service.finders.PassengerFinderByIdService;
import br.com.cwi.crescer.melevaai.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyPrinterService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PassengerFinderByIdService passengerFinderByIdService;
    @Autowired
    private  Validators validators;


    public MoneyPrinterResponse post(MoneyPrinterRequest request) {
        Passenger passenger = passengerFinderByIdService.findByIdWithException(request.getPassengerId());
        validators.verifyIfMoneyInputIsBiggerThanZero(request.getMoneyToAdd());
        passenger.setBalance(passenger.getBalance().add(request.getMoneyToAdd()));
        passengerRepository.save(passenger);
        MoneyPrinterResponse response = new MoneyPrinterResponse();
        response.setMoneyResponse("Você depositou "+ request.getMoneyToAdd()+ " na sua conta! Agora você tem: "+passenger.getBalance());
        return response;
    }
}
