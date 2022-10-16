package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ListCoursesNotInTeamService {

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    @Autowired
    private CourseListMapper courseMapper;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    public List<CourseResponse> listCoursesNotInTeam() {

        List<Long> ids =  userAccountAuthenticatedService.get().getTeam().getCourses()
                .stream()
                .map(course -> course.getId())
                .collect(Collectors.toList());

        List<Course> courses = findCourseWithThrow.findByApprovementStatusNotInTeamWithException(true, true, ids);

        return courseMapper.toResponseList(courses);
    }
}