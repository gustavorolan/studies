package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class VerifyPersonStatusService {

  public void ifDriverIsNotLivreThrowsAnException(Driver driver) {
    if(driver.getPersonStatus() != PersonStatus.LIVRE) {
      throw new ResponseStatusException(NOT_FOUND, "Motorista nao esta livre");
    }
  }

  public void ifPassengerIsNotLivreThrowsAnException(Passenger passenger) {
    if(passenger.getPersonStatus() != PersonStatus.LIVRE) {
      throw new ResponseStatusException(NOT_FOUND, "Passageiro nao esta livre");
    }
  }
}
