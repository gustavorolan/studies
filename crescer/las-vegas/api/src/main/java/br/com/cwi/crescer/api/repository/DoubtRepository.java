package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Doubt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoubtRepository extends JpaRepository<Doubt, Long> {
    Optional<Page<Doubt>> findByActiveOrderByDateTimeCreationDesc(Boolean active, Pageable pageable);

    Optional<Doubt> findByIdAndActive(Long id, Boolean active);
}
