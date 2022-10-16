package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("select v from Video v " +
            "where v.active = ?1 and (v.name like concat('%', ?2, '%') or v.description like concat('%', ?3, '%'))")
    Optional<Page<Video>> findByActiveAndNameOrDescription(Boolean active, String name, String description, Pageable pageable);

    Optional<Video> findByIdAndActive(Long id, Boolean active);

    Optional<Page<Video>> findByAndActiveAndTypeAndNameContains(Boolean active, VideoType type, String name, Pageable pageable);
}