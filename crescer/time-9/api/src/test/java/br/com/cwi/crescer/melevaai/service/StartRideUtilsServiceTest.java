package br.com.cwi.crescer.melevaai.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StartRideUtilsServiceTest {

  @InjectMocks
  private StartRideUtilsService service;

  @Test
  @DisplayName("Deve calcular distancia entre dois pontos corretamente")
  public void deveCalcularDistanciaEntreDoisPontosCorretamente() {
    Integer endX = 5;
    Integer startX = 1;
    Integer endY = 5;
    Integer startY = 1;

    double expectedResponse = 5.656854249492381;

    double response = service.getRideDistance(endX, startX, endY, startY);

    assertEquals(expectedResponse, response);
  }

  @Test
  @DisplayName("Deve calcular o tempo estimado corretamente")
  public void deveCalcularOTempoEstimadoCorretamente() {
    double distance = 50;
    int estimatedArrivalTime = service.getEstimatedArrivalTime(distance);

    int expectedResult = 6000;

    assertEquals(expectedResult, estimatedArrivalTime);
  }

  @Test
  @DisplayName("Deve calcular o preço estimado corretamente")
  public void deveCalcularOPrecoEstimadoCorretamente() {
    int estimatedTime = 500;
    BigDecimal estimatedPrice = service.getEstimatedPrice(estimatedTime);

    BigDecimal expectedResult = new BigDecimal("100.0");

    assertEquals(expectedResult, estimatedPrice);
  }

}