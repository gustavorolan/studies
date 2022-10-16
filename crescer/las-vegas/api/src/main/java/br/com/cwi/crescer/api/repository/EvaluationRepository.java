package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.security.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Optional<Evaluation> findByIdAndActive(Long id, Boolean active);

    Optional<Evaluation> findByVideoAndUserAccount(Video video, UserAccount user);

    Optional<Page<Evaluation>> findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreDesc(Long videoId, Boolean active, String comment, Integer score, Pageable pageable);

    Optional<Page<Evaluation>> findByVideoIdAndActiveAndCommentContainsOrderByScoreDesc(Long videoId, Boolean active, String comment, Pageable pageable);

    Optional<Page<Evaluation>> findByVideoIdAndActiveAndCommentContainsOrderByScoreAsc(Long videoId, Boolean active, String comment, Pageable pageable);

    Optional<Page<Evaluation>> findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreAsc(Long videoId, Boolean active, String comment, Integer score, Pageable pageable);

    @Query("select v from Evaluation v where v.userAccount.id=?1 and v.video.id=?2")
    Optional<Evaluation> findEvaluation(Long authorId, Long videoId);

    @Query("select v from Evaluation v where v.userAccount.id=?1")
    List<Evaluation> findEvaluationsByUser(Long authorId);
}