package br.com.cwi.crescer.melevaai.service.finders;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PassengerFinderByIdService {
    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger findByIdWithException(Long id) {
        return passengerRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "passageiro nao existe"));
        }
}
