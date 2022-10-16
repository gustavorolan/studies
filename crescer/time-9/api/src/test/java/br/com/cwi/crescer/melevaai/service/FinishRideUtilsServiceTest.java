package br.com.cwi.crescer.melevaai.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinishRideUtilsServiceTest {

  @InjectMocks
  private FinishRideUtilsService service;

  @Test
  @DisplayName("Must calculate ride price correctly")
  public void mustCalculateRidePriceCorrectly() {
    int timeInSeconds = 100;

    BigDecimal expectedResult = new BigDecimal("20.0");
    BigDecimal response = service.getRidePrice(timeInSeconds);
    assertEquals(expectedResult, response);
  }

  @Test
  @DisplayName("Must calculate ride time in seconds correctly")
  public void mustCalculateRideTimeInSecondsCorrectly() {
    LocalDateTime dateNow = LocalDateTime.now();
    LocalDateTime dateRideStart = dateNow.minus(Duration.ofSeconds(50));

    int expectedResult = 50;

    int timeInSeconds = service.getRideTimeInSeconds(dateRideStart, dateNow);

    assertEquals(expectedResult, timeInSeconds);
  }

}