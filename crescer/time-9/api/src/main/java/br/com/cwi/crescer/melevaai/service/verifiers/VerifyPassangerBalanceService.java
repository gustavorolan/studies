package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VerifyPassangerBalanceService {

  public void verifiyBalance(Passenger passenger, BigDecimal price) {
    if(passenger.getBalance().compareTo(price) < 0) {
      throw new ResponseStatusException(NOT_FOUND, "Passageiro nao tem saldo suficiente");
    }
  }
}
