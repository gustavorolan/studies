package br.com.cwi.crescer.melevaai.util;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DriverFactory {

  public static Driver get() {
    return getBuilder().build();
  }

  public static Driver.DriverBuilder getBuilder() {
    return Driver.builder()
        .nameDriver("driver testes")
        .balance(new BigDecimal("20"))
        .cpf("00987654321")
        .personStatus(PersonStatus.LIVRE)
        .score(5)
        .birthDate(LocalDate.now());
  }
}
