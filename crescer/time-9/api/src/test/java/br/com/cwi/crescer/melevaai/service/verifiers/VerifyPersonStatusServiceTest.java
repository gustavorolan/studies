package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VerifyPersonStatusServiceTest {

  @InjectMocks
  private VerifyPersonStatusService service;

  @Test
  @DisplayName("Deve dar erro quando o motorista nao estiver livre")
  public void deveDarErroQuandoOMotoristaNaoEstiverLivre() {
    Driver driver = DriverFactory.get();
    driver.setPersonStatus(PersonStatus.OCUPADO);

    Exception exception = assertThrows(ResponseStatusException.class, () ->
        service.ifDriverIsNotLivreThrowsAnException(driver));

    ResponseStatusException expectedResult = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Motorista nao esta livre");

    assertEquals(expectedResult.getMessage(), exception.getMessage());
  }

  @Test
  @DisplayName("Deve dar erro quando o passageiro nao estiver livre")
  public void deveDarErroQuandoOPAssageiroNaoEstiverLivre() {
    Passenger passenger = PassengerFactory.get();
    passenger.setPersonStatus(PersonStatus.OCUPADO);

    Exception exception = assertThrows(ResponseStatusException.class, () ->
        service.ifPassengerIsNotLivreThrowsAnException(passenger));

    ResponseStatusException expectedResult = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Passageiro nao esta livre");

    assertEquals(expectedResult.getMessage(), exception.getMessage());
  }

}