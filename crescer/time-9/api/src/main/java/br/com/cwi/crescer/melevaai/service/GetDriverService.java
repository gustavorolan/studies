package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetDriverResponse;
import br.com.cwi.crescer.melevaai.mapper.GetDriverMapper;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDriverService {

  @Autowired
  private DriverFinderByIdService driverFinderByIdService;

  @Autowired
  private GetDriverMapper getDriverMapper;

  public GetDriverResponse getDriver(Long driverId) {
    Driver driver = driverFinderByIdService.findByIdWithException(driverId);
    return getDriverMapper.toResponse(driver);
  }
}
