package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetDetailedCourseService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private CourseListMapper courseMapper;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    public CourseCompleteResponse getDetailedCourse(Long teamId) {
        Course course = findCourseWithThrow.findByIdWithException(teamId);

        return courseMapper.toResponseWithVideos(course);
    }
}
