package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.EvaluationFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import br.com.cwi.crescer.api.validator.VerifyIfEvaluationWasAlreadyMadeValidator;
import br.com.cwi.crescer.api.validator.VerifyIfScoreIsInRangeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class VideoAssesServiceTest {
    @InjectMocks
    private VideoAssesService videoAssesService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private EvaluationRepository evaluationRepository;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private VerifyIfEvaluationWasAlreadyMadeValidator verifyIfEvaluationWasAlreadyMadeValidator;

    @Mock
    private VerifyIfScoreIsInRangeValidator verifyIfScoreIsInRangeValidator;

    @Captor
    private ArgumentCaptor<Evaluation> evaluationArgumentCaptor;

    @Captor
    private ArgumentCaptor<UserAccount> userAccountArgumentCaptor;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;

    @Test
    @DisplayName("Should evaluate a video")
    void toAsses() {
        int expectedAverage = 3;
        UserAccount userAccount = UserAccountFactory.get();
        Course course = CourseFactory.getCourse();
        String comment = "";
        int score = 0;
        Long videoId = 1L;
        Evaluation evaluationOne = EvaluationFactory.getBuilder().score(5).build();
        Evaluation evaluationTwo = Evaluation.builder().score(5).build();
        List<Evaluation> evaluationList = new ArrayList<>();
        evaluationList.add(evaluationOne);
        evaluationList.add(evaluationTwo);


        Video video = VideoFactory.getBuilder()
                .id(2L)
                .author(userAccount)
                .course(course)
                .evaluations(evaluationList)
                .build();


        userAccount.setCreatedVideos(List.of(video));
        course.setVideos(List.of(video));

        Mockito.when(findVideoWithThrow.findByIdWithException(videoId)).thenReturn(video);
        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);

        videoAssesService.toAsses(1L,comment,score);

        Mockito.verify(findVideoWithThrow).findByIdWithException(videoId);
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(verifyIfScoreIsInRangeValidator).verifyIfScoreIsInRange(score, 0, 5);
        Mockito.verify(verifyIfEvaluationWasAlreadyMadeValidator).verifyIfEvaluationWasAlreadyMade(video, userAccount);
        Mockito.verify(evaluationRepository).save(evaluationArgumentCaptor.capture());
        Mockito.verify(userAccountRepository).save(userAccountArgumentCaptor.capture());
        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());


        Integer evaluationValue = evaluationArgumentCaptor.getValue().getVideo().getScore();
        Integer userValue = userAccountArgumentCaptor.getValue().getAssessment();
        Integer courseValue = courseArgumentCaptor.getValue().getAssessment();

        Assertions.assertEquals(expectedAverage,evaluationValue);
        Assertions.assertEquals(expectedAverage,userValue);
        Assertions.assertEquals(expectedAverage,courseValue);


    }
}