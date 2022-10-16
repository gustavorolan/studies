package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.DeactivateDriverResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPersonStatusService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeactivateDriverServiceTest {

  @InjectMocks
  private DeactivateDriverService service;

  @Mock
  private DriverRepository driverRepository;

  @Mock
  private VerifyPersonStatusService verifyPersonStatusService;

  @Mock
  private DriverFinderByIdService driverFinderByIdService;

  @Captor
  private ArgumentCaptor<Driver> driverArgumentCaptor;

  @Test
  @DisplayName("Deve desativar motorista com sucesso")
  public void deveDesativarMotoristaComSucesso() {

    Long driverId = 1L;

    Driver driver = DriverFactory.get();

    when(driverFinderByIdService.findByIdWithException(driverId))
        .thenReturn(driver);

    DeactivateDriverResponse serviceResponse = service.deactivateDriver(driverId);
    String expectedServiceResponse = "Motorista "+ driver.getNameDriver() +" desativado com sucesso";

    verify(driverRepository).save(driverArgumentCaptor.capture());
    Driver driverUsedInTest = driverArgumentCaptor.getValue();

    assertEquals(PersonStatus.INATIVO, driverUsedInTest.getPersonStatus());
    assertEquals(expectedServiceResponse, serviceResponse.getMessage());
  }

}
