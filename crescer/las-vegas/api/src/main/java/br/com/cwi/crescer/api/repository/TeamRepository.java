package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByIdAndActive(Long id, Boolean active);
}