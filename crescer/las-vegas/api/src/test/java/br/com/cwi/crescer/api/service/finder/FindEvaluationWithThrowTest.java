package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FindEvaluationWithThrowTest {
    @InjectMocks
    private FindEvaluationWithThrow findEvaluationWithThrow;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Test
    @DisplayName("Should find doubt")
    void findById() {
        Long id = 1L;
        Boolean active = true;

        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        Mockito.when(evaluationRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.ofNullable(evaluation));

        Evaluation value = findEvaluationWithThrow.findByIdWithException(id, active);

        Mockito.verify(evaluationRepository).findByIdAndActive(id, active);

        Assertions.assertEquals(evaluation, value);
    }

    @Test
    @DisplayName("Should throw error when trying to find doubt")
    void findByIdWithError() {
        Long id = 1L;
        Boolean active = true;

        Mockito.when(evaluationRepository.findByIdAndActive(id, active))
                .thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findEvaluationWithThrow.findByIdWithException(id, active);
        });

        Mockito.verify(evaluationRepository).findByIdAndActive(id, active);

        Assertions.assertEquals("404 NOT_FOUND \"Evaluation does not exist\"", exception.getMessage());
    }


    @Test
    @DisplayName("Should find doubts with desc ordenation and active and score parameters")
    void findByDesc() {
        Long videoId = 1L;
        String filter = "comment";
        String score = "5";
        Boolean active = true;
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        Page<Evaluation> pageResponse = new PageImpl<Evaluation>(List.of(evaluation));

        Mockito.when(evaluationRepository.findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreDesc(videoId, active, filter, Integer.valueOf(score), pageable))
                .thenReturn(Optional.of(pageResponse));

        Page<Evaluation> value = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrScoreDescWithException(videoId, active, filter, Integer.valueOf(score), pageable);

        Mockito.verify(evaluationRepository).findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreDesc(videoId, active, filter, Integer.valueOf(score), pageable);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should find doubts with desc ordenation and active parameter")
    void findByDescWithoutScore() {
        Long videoId = 1L;
        String filter = "comment";
        Boolean active = true;
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Evaluation evaluation = EvaluationFactory.getBuilder().build();
        evaluation.setScore(2);

        Page<Evaluation> pageResponse = new PageImpl<Evaluation>(List.of(evaluation));

        Mockito.when(evaluationRepository.findByVideoIdAndActiveAndCommentContainsOrderByScoreDesc(videoId, active, filter, pageable))
                .thenReturn(Optional.of(pageResponse));

        Page<Evaluation> value = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreDescWithException(videoId, active, filter, pageable);

        Mockito.verify(evaluationRepository).findByVideoIdAndActiveAndCommentContainsOrderByScoreDesc(videoId, active, filter, pageable);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should find doubts with asc ordenation and active and score parameters")
    void findByAsc() {
        Long videoId = 1L;
        String filter = "comment";
        String score = "5";
        Boolean active = true;
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Evaluation evaluation = EvaluationFactory.getBuilder().build();

        Page<Evaluation> pageResponse = new PageImpl<Evaluation>(List.of(evaluation));

        Mockito.when(evaluationRepository.findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreAsc(videoId, active, filter, Integer.valueOf(score), pageable))
                .thenReturn(Optional.of(pageResponse));

        Page<Evaluation> value = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentAndScoreOrderByScoreAscWithException(videoId, active, filter, Integer.valueOf(score), pageable);

        Mockito.verify(evaluationRepository).findByVideoIdAndActiveAndCommentContainsAndScoreOrderByScoreAsc(videoId, active, filter, Integer.valueOf(score), pageable);

        Assertions.assertEquals(pageResponse, value);
    }

    @Test
    @DisplayName("Should find doubts with asc ordenation and active parameter")
    void findByAscWithoutScore() {
        Long videoId = 1L;
        String filter = "comment";
        Boolean active = true;
        Pageable pageable = Pageable.ofSize(1).withPage(0);

        Evaluation evaluation = EvaluationFactory.getBuilder().build();
        evaluation.setScore(2);

        Page<Evaluation> pageResponse = new PageImpl<Evaluation>(List.of(evaluation));

        Mockito.when(evaluationRepository.findByVideoIdAndActiveAndCommentContainsOrderByScoreAsc(videoId, active, filter, pageable))
                .thenReturn(Optional.of(pageResponse));

        Page<Evaluation> value = findEvaluationWithThrow.findByVideoIdAndActiveAndCommentOrderByScoreAscWithException(videoId, active, filter, pageable);

        Mockito.verify(evaluationRepository).findByVideoIdAndActiveAndCommentContainsOrderByScoreAsc(videoId, active, filter, pageable);

        Assertions.assertEquals(pageResponse, value);
    }
}
