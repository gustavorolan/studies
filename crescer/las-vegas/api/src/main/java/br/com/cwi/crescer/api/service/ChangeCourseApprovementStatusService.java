package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangeCourseApprovementStatusService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;


    public ResponseMessage changeCourseApprovementStatus(Long courseId) {
        Course course = findCourseWithThrow.findByIdWithException(courseId);
        Boolean approvementStatus = course.getApprovementStatus();
        course.setApprovementStatus(!approvementStatus);
        courseRepository.save(course);
        return ResponseMessage.builder().response("You've changed successfully").build();
    }
}
