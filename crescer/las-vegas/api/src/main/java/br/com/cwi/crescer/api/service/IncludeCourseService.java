package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;

@Service
public class IncludeCourseService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ParametersRegexValidator parametersRegexValidator;

    @Autowired
    private FindAttachmentWithThrow findAttachmentWithThrow;

    public CourseResponse includeCourse(CourseRequest request, String imageId) {
        parametersRegexValidator.verifyIfImageIdIsValid(imageId);

        UserAccount loggedUserAccount = userAccountAuthenticatedService.get();

        Attachment attachment = findAttachmentWithThrow.findByIdWithException(imageId);

        Course course = courseMapper.toEntity(request);

        course.setAuthor(loggedUserAccount);
        course.setImage(attachment);
        course.setDateTimeCreation(LocalDateTime.now());
        course.setActive(true);
        course.setAssessment(SCORE.getScore());
        course.setApprovementStatus(false);
        loggedUserAccount.getCreatedCourses().add(course);
        course.setAuthor(loggedUserAccount);
        courseRepository.save(course);
        return courseMapper.toResponse(course);
    }}