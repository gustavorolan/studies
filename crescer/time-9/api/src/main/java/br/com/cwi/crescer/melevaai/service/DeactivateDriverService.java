package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.DeactivateDriverResponse;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import br.com.cwi.crescer.melevaai.service.verifiers.VerifyPersonStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeactivateDriverService {

  @Autowired
  private DriverRepository driverRepository;

  @Autowired
  private VerifyPersonStatusService verifyPersonStatusService;

  @Autowired
  private DriverFinderByIdService driverFinderByIdService;


  public DeactivateDriverResponse deactivateDriver(Long driverId) {
    Driver driver = driverFinderByIdService.findByIdWithException(driverId);

    verifyPersonStatusService.ifDriverIsNotLivreThrowsAnException(driver);

    driver.setPersonStatus(PersonStatus.INATIVO);

    driverRepository.save(driver);

    DeactivateDriverResponse response = new DeactivateDriverResponse();
    response.setMessage("Motorista "+ driver.getNameDriver() +" desativado com sucesso");

    return response;
  }
}
