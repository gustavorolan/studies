package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindEvaluationWithThrow {

    @Autowired
    private EvaluationRepository evaluationRepository;

    private static final String RESPONSE = "Evaluation does not exist";

    public Evaluation findByIdWithException(Long id, Boolean active) {
        return evaluationRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Evaluation> findByVideoIdAndActiveAndCommentOrScoreDescWithException(Long videoId, Boolean active, String filter, Integer integerScore, Pageable pageable) {
        return evaluationRepository.findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreDesc(videoId, active, filter, integerScore, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Evaluation> findByVideoIdAndActiveAndCommentOrderByScoreDescWithException(Long videoId, Boolean active, String filter, Pageable pageable) {
        return evaluationRepository.findByVideoIdAndActiveAndCommentContainsOrderByScoreDesc(videoId, active, filter, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Evaluation> findByVideoIdAndActiveAndCommentAndScoreOrderByScoreAscWithException(Long videoId, Boolean active, String filter, Integer integerScore, Pageable pageable) {
        return evaluationRepository.findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreAsc(videoId, active, filter, integerScore, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Page<Evaluation> findByVideoIdAndActiveAndCommentOrderByScoreAscWithException(Long videoId, Boolean active, String filter, Pageable pageable) {
        return evaluationRepository.findByVideoIdAndActiveAndCommentContainsOrderByScoreAsc(videoId, active, filter, pageable)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}