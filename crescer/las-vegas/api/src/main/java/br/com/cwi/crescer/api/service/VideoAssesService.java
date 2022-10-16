package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Evaluation;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.repository.EvaluationRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.repository.UserAccountRepository;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.validator.VerifyIfScoreIsInRangeValidator;
import br.com.cwi.crescer.api.validator.VerifyIfEvaluationWasAlreadyMadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoAssesService {
    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private VerifyIfEvaluationWasAlreadyMadeValidator verifyIfEvaluationWasAlreadyMadeValidator;

    @Autowired
    private VerifyIfScoreIsInRangeValidator verifyIfScoreIsInRangeValidator;

    public ResponseMessage toAsses(Long videoId,String comment, int score) {
        Video video = findVideoWithThrow.findByIdWithException(videoId);

        UserAccount userAccount = userAccountAuthenticatedService.get();

        verifyIfScoreIsInRangeValidator.verifyIfScoreIsInRange(score, 0, 5);
        verifyIfEvaluationWasAlreadyMadeValidator.verifyIfEvaluationWasAlreadyMade(video, userAccount);

        Integer integerScore = score;

        Evaluation evaluation = Evaluation.builder()
                .dateTimeCreation(LocalDateTime.now())
                .video(video)
                .comment(comment)
                .score(integerScore)
                .userAccount(userAccount)
                .active(true)
                .build();

        Integer videoAsses = videoAverageCalculator(video.getEvaluations(),score);
        video.setScore(videoAsses);

        evaluationRepository.save(evaluation);

        UserAccount author = video.getAuthor();
        int average = averageCalculator(author.getCreatedVideos());
        author.setAssessment(average);

        userAccountRepository.save(author);


        if (video.getCourse() != null) {
            Course course = video.getCourse();
            Integer courseAverage = averageCalculator(course.getVideos());
            course.setAssessment(courseAverage);
            courseRepository.save(course);
        }

        return ResponseMessage.builder().response("You've rated with success").build();
    }


    private int averageCalculator (List<Video> evaluationsVideos){

        int scoreSum = evaluationsVideos.stream()
                .map(Video::getScore)
                .reduce(0, Integer::sum);

        return  scoreSum/evaluationsVideos.toArray().length;
    }


    private int videoAverageCalculator (List<Evaluation> evaluationsVideo, int score ){
        int scoreSum = evaluationsVideo.stream()
                .map(Evaluation::getScore)
                .reduce(0, Integer::sum);

        return  (scoreSum+score)/(evaluationsVideo.toArray().length+1);
    }
}
