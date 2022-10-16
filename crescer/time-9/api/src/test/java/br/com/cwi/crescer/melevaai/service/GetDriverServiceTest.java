package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetDriverResponse;
import br.com.cwi.crescer.melevaai.mapper.GetDriverMapper;
import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.service.finders.DriverFinderByIdService;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetDriverServiceTest {
    @InjectMocks
    private GetDriverService getDriverService;
    @Mock
    private DriverFinderByIdService driverFinderByIdService;
    @Mock
    private GetDriverMapper getDriverMapper;

    @Test
    @DisplayName("Should return a driver correctly")
    void getDriverTest() {
        long driverId = 1L;
        Driver driver = DriverFactory.getBuilder().build();

        GetDriverResponse getDriverResponse =  new GetDriverResponse();
        getDriverResponse.setNameDriver("driver testes");
        getDriverResponse.setBalance(new BigDecimal("20"));
        getDriverResponse.setPersonStatus(PersonStatus.LIVRE);

        when(driverFinderByIdService.findByIdWithException(driverId)).thenReturn(driver);
        when(getDriverMapper.toResponse(driver)).thenReturn(getDriverResponse);

        getDriverService.getDriver(driverId);

        verify(getDriverMapper).toResponse(driver);
        verify(driverFinderByIdService).findByIdWithException(driverId);

    }
}