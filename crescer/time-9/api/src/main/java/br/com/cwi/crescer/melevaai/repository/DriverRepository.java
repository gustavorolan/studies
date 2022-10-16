package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("select d from Driver d where d.driverLicense.expireDate > ?1 order by d.score desc")
    List<Driver> driverFilterToStartRide(LocalDate date);
}

