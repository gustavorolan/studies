package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.mapper.EvaluationMapper;
import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.service.finder.FindEvaluationWithThrow;
import br.com.cwi.crescer.api.util.EvaluationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllVideoEvaluationsServiceTest {

    @InjectMocks
    private GetAllVideoEvaluationsService getAllVideoEvaluationsService;

    @Mock
    private EvaluationMapper evaluationMapper;

    @Mock
    private FindEvaluationWithThrow findEvaluationWithThrow;

    @Test
    @DisplayName("Should get all videos evaluations with desc ordenation and filter and score parameters")
    void getAllVideoEvaluationsDescWithScore() {
        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        EvaluationResponse evaluationResponse = EvaluationFactory.getBuilderResponse().build();

        Page<Evaluation> page = new PageImpl<Evaluation>(List.of(evaluation));

        Page<EvaluationResponse> pageResponse = new PageImpl<EvaluationResponse>(List.of(evaluationResponse));

        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Long videoId = 1L;
        String filter = "comment";
        String score = "5";
        String ordenation = "Desc";
        Boolean active = true;

        Mockito.when(findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrScoreDescWithException(videoId, active, filter, Integer.valueOf(score), pageable))
                        .thenReturn(page);

        Mockito.when(evaluationMapper.toResponse(evaluation))
                .thenReturn(evaluationResponse);

        Page<EvaluationResponse> value = getAllVideoEvaluationsService.getAllVideoEvaluations(pageable, videoId, filter, score, ordenation);

        Mockito.verify(findEvaluationWithThrow).findByVideoIdAndActiveAndCommentOrScoreDescWithException(videoId, active, filter, Integer.valueOf(score), pageable);
        Mockito.verify(evaluationMapper).toResponse(evaluation);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should get all videos evaluations with desc ordenation and filter parameter")
    void getAllVideoEvaluationsDesc() {
        Evaluation evaluation = EvaluationFactory.getBuilder().build();
        evaluation.setScore(2);

        EvaluationResponse evaluationResponse = EvaluationFactory.getBuilderResponse().build();
        evaluationResponse.setScore(2);

        Page<Evaluation> page = new PageImpl<Evaluation>(List.of(evaluation));

        Page<EvaluationResponse> pageResponse = new PageImpl<EvaluationResponse>(List.of(evaluationResponse));

        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Long videoId = 1L;
        String filter = "comment";
        String score = "";
        String ordenation = "Desc";
        Boolean active = true;

        Mockito.when(findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreDescWithException(videoId, active, filter, pageable))
                .thenReturn(page);

        Mockito.when(evaluationMapper.toResponse(evaluation))
                .thenReturn(evaluationResponse);

        Page<EvaluationResponse> value = getAllVideoEvaluationsService.getAllVideoEvaluations(pageable, videoId, filter, score, ordenation);

        Mockito.verify(findEvaluationWithThrow).findByVideoIdAndActiveAndCommentOrderByScoreDescWithException(videoId, active, filter, pageable);
        Mockito.verify(evaluationMapper).toResponse(evaluation);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should get all videos evaluations with asc ordenation and filter and score parameters")
    void getAllVideoEvaluationsAscWithScore() {
        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        EvaluationResponse evaluationResponse = EvaluationFactory.getBuilderResponse().build();

        Page<Evaluation> page = new PageImpl<Evaluation>(List.of(evaluation));

        Page<EvaluationResponse> pageResponse = new PageImpl<EvaluationResponse>(List.of(evaluationResponse));

        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Long videoId = 1L;
        String filter = "comment";
        String score = "5";
        String ordenation = "Asc";
        Boolean active = true;

        Mockito.when(findEvaluationWithThrow.findByVideoIdAndActiveAndCommentAndScoreOrderByScoreAscWithException(videoId, active, filter, Integer.valueOf(score), pageable))
                .thenReturn(page);

        Mockito.when(evaluationMapper.toResponse(evaluation))
                .thenReturn(evaluationResponse);

        Page<EvaluationResponse> value = getAllVideoEvaluationsService.getAllVideoEvaluations(pageable, videoId, filter, score, ordenation);

        Mockito.verify(findEvaluationWithThrow).findByVideoIdAndActiveAndCommentAndScoreOrderByScoreAscWithException(videoId, active, filter, Integer.valueOf(score), pageable);
        Mockito.verify(evaluationMapper).toResponse(evaluation);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should get all videos evaluations with asc ordenation and filter parameter")
    void getAllVideoEvaluationsAsc() {
        Evaluation evaluation = EvaluationFactory.getBuilder().build();
        evaluation.setScore(2);

        EvaluationResponse evaluationResponse = EvaluationFactory.getBuilderResponse().build();
        evaluationResponse.setScore(2);

        Page<Evaluation> page = new PageImpl<Evaluation>(List.of(evaluation));

        Page<EvaluationResponse> pageResponse = new PageImpl<EvaluationResponse>(List.of(evaluationResponse));

        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Long videoId = 1L;
        String filter = "comment";
        String score = "";
        String ordenation = "Asc";
        Boolean active = true;

        Mockito.when(findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreAscWithException(videoId, active, filter, pageable))
                .thenReturn(page);

        Mockito.when(evaluationMapper.toResponse(evaluation))
                .thenReturn(evaluationResponse);

        Page<EvaluationResponse> value = getAllVideoEvaluationsService.getAllVideoEvaluations(pageable, videoId, filter, score, ordenation);

        Mockito.verify(findEvaluationWithThrow).findByVideoIdAndActiveAndCommentOrderByScoreAscWithException(videoId, active, filter, pageable);
        Mockito.verify(evaluationMapper).toResponse(evaluation);

        Assertions.assertEquals(pageResponse, value);
    }
}