package br.com.cwi.crescer.melevaai.service.finders;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import br.com.cwi.crescer.melevaai.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RideFinderByIdService {

  @Autowired
  private RideRepository rideRepository;

  public Ride findByIdWithException(Long id) {
    return rideRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "corrida nao existe"));
  }
}
