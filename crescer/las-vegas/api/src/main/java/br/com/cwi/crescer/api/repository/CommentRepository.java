package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.model.Doubt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndActive(Long id, Boolean active);

    Optional<Page<Comment>> findByDoubtAndActive(Doubt doubt, Boolean active, Pageable pageable);
}
