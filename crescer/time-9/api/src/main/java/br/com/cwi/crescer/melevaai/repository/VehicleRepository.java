package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.driver.Vehicle;
import br.com.cwi.crescer.melevaai.model.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
