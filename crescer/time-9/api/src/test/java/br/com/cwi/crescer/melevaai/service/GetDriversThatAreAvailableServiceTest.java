package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.util.DriverFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetDriversThatAreAvailableServiceTest {
    @InjectMocks
    private GetDriversThatAreAvailableService getDriversThatAreAvailableService;

    @Test
    @DisplayName("Should return all available drivers from a driver list")
    void getDriversThatAreAvailableTest() {
        Driver driver = DriverFactory.getBuilder().personStatus(PersonStatus.INATIVO).build();
        Driver driverTwo = DriverFactory.getBuilder().personStatus(PersonStatus.LIVRE).build();
        Driver driverThree = DriverFactory.getBuilder().personStatus(PersonStatus.OCUPADO).build();

        List<Driver> driverListInput = new ArrayList<Driver>();
        driverListInput.add(driver);
        driverListInput.add(driverTwo);
        driverListInput.add(driverThree);

        List<Driver> driverListResponse = new ArrayList<Driver>();
        driverListResponse.add(driverTwo);

        List<Driver> result = getDriversThatAreAvailableService.getDriversThatAreAvailable(driverListInput);

        assertEquals(driverListResponse,result);

    }
}