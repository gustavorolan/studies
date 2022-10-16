package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.PersonStatus;
import br.com.cwi.crescer.melevaai.model.driver.Driver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDriversThatAreAvailableService {
    public List<Driver> getDriversThatAreAvailable(List<Driver> drivers) {
        return drivers.stream()
                .filter(driver -> driver.getPersonStatus() == PersonStatus.LIVRE)
                .collect(Collectors.toList());
    }

}
