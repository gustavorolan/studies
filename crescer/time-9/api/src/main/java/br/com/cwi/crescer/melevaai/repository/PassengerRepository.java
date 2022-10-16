package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
