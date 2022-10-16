package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.ride.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
