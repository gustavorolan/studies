package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<List<Course>> findByActiveAndApprovementStatus(Boolean active, Boolean approvementStatus);

    Optional<Course> findByIdAndActive(Long id, Boolean active);

    @Query("select c from Course c where c.active = ?1 and c.approvementStatus = ?2 and c.id not in ?3")
    Optional<List<Course>> findByActiveAndApprovementStatusAndIdNotIn(Boolean active, Boolean approvementStatus, List<Long> ids);
}