package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.mapper.EvaluationMapper;
import br.com.cwi.crescer.api.service.finder.FindEvaluationWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllVideoEvaluationsService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private FindEvaluationWithThrow findEvaluationWithThrow;

    public Page<EvaluationResponse> getAllVideoEvaluations(Pageable pageable, Long videoId, String filter, String score, String ordenation) {
        Page<EvaluationResponse> evaluations = null;

        if (ordenation.equals("Desc")) {
            if (!score.isBlank()) {
                Integer integerScore = Integer.valueOf(score);
                evaluations = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrScoreDescWithException(videoId, true, filter, integerScore, pageable).map(evaluation -> evaluationMapper.toResponse(evaluation));
            } else {
                evaluations = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreDescWithException(videoId, true, filter, pageable).map(evaluation -> evaluationMapper.toResponse(evaluation));
            }
        } else {
            if (!score.isBlank()) {
                Integer integerScore = Integer.valueOf(score);
                evaluations = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentAndScoreOrderByScoreAscWithException(videoId, true, filter, integerScore, pageable).map(evaluation -> evaluationMapper.toResponse(evaluation));
            } else {
                evaluations = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreAscWithException(videoId, true, filter, pageable).map(evaluation -> evaluationMapper.toResponse(evaluation));
            }
        }

        return evaluations;
    }
}