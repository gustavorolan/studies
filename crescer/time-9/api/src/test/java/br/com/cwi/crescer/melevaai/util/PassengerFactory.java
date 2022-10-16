package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PassengerFactory {

  public static Passenger get() {
    return getBuilder().build();
  }

  public static Passenger.PassengerBuilder getBuilder() {
    return Passenger.builder()
        .namePassenger("passageiro testes")
        .balance(new BigDecimal("20"))
        .cpf("12345678900")
        .personStatus(PersonStatus.LIVRE)
        .score(5)
        .birthDate(LocalDate.now());
  }
}
