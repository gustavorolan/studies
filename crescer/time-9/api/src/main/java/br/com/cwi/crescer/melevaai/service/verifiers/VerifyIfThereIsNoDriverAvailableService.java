package br.com.cwi.crescer.melevaai.service.verifiers;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyIfThereIsNoDriverAvailableService {
    public void throwAnErrorIfThereIsNoDriversAvailable(List<Driver> drivers){
        if(drivers.isEmpty()) throw new RuntimeException("Não tem motorista disponivel");
    };
}
