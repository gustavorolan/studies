package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.util.PassengerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VerifyPassangerBalanceServiceTest {

  @InjectMocks
  private VerifyPassangerBalanceService service;

  @Test
  @DisplayName("Deve dar erro quando o passageiro nao tiver saldo suficiente")
  public void deveDarErroQuandoOPassageiroNaoTiverSaldoSuficiente() {
    Passenger passenger = PassengerFactory.get();
    BigDecimal price = new BigDecimal("9999999999");

    Exception exception = assertThrows(ResponseStatusException.class, () ->
        service.verifiyBalance(passenger, price));

    ResponseStatusException expectedResult = new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Passageiro nao tem saldo suficiente");

    assertEquals(expectedResult.getMessage(), exception.getMessage());
  }

}