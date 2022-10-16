package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeCourseActiveStatusService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    @Autowired
    private LocalDateAndTimeService localDateAndTimeService;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private CourseMapper courseMapper;

    public ResponseMessage changeCourseActiveStatus(Long courseId) {
        Course course = findCourseWithThrow.findByIdAndActiveWithException(courseId, true);

        permissionValidator.validateLoggedUserPermission(course.getAuthor());

        Boolean isCourseActive = course.getActive();

        course.setActive(!isCourseActive);
        course.setDateTimeUpdate(localDateAndTimeService.getLocalDateTime());

        courseRepository.save(course);

        return ResponseMessage.builder().response("You've changed successfully").build();
    }
}
