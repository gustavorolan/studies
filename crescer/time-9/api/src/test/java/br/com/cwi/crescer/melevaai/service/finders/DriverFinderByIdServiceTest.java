package br.com.cwi.crescer.melevaai.service.finders;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class DriverFinderByIdServiceTest {

  @InjectMocks
  private DriverFinderByIdService service;

  @Mock
  private DriverRepository driverRepository;

  @Test
  @DisplayName("Deve buscar driver com sucesso")
  void deveBuscarDriverComSucesso() {
    Long driverId = 1L;

    Driver driver = new Driver();

    Mockito.when(driverRepository.findById(driverId)).thenReturn(java.util.Optional.of(driver));

    service.findByIdWithException(driverId);

    Mockito.verify(driverRepository).findById(driverId);
  }

  @Test
  @DisplayName("Deve retornar erro ao nao encontrar driver")
  void deveRetornarErroAoNaoEncontrarDriver() {
    Long driverId = 1L;

    Exception exception = Assertions.assertThrows(ResponseStatusException.class, () ->
        service.findByIdWithException(driverId));

    ResponseStatusException expectedResult = new ResponseStatusException(HttpStatus.NOT_FOUND, "driver nao existe");

    Assertions.assertEquals(expectedResult.getMessage(), exception.getMessage());
  }
}
