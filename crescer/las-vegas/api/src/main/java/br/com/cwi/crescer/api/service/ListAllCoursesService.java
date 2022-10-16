package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListAllCoursesService {

    @Autowired
    private CourseListMapper courseMapper;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    public List<CourseResponse> listAllCourses() {
        List<Course> courses = findCourseWithThrow.findByApprovementStatusWithException(true, true);

        return courseMapper.toResponseList(courses);
    }
}
