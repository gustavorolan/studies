package br.com.cwi.crescer.melevaai.service.finders;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DriverFinderByIdService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver findByIdWithException(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "driver nao existe"));
    }
}
