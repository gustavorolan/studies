package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListPendentCoursesServiceTest {
    @InjectMocks
    private  ListPendentCoursesService list;

    @Mock
    private CourseListMapper courseMapper;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Test
    void listPendentCourses() {
        Course course = CourseFactory.getCourse();
        CourseResponse courseResponse = CourseFactory.getCourseResponse();

        Mockito.when(findCourseWithThrow.findByApprovementStatusWithException(true, false)).thenReturn(List.of(course));
        Mockito.when(courseMapper.toResponseList(List.of(course))).thenReturn(List.of(courseResponse));

        List<CourseResponse> listPendentCourses = list.listPendentCourses();

        Mockito.verify(findCourseWithThrow).findByApprovementStatusWithException(true, false);
        Mockito.verify(courseMapper).toResponseList(List.of(course));

        Assertions.assertEquals(List.of(courseResponse),listPendentCourses);
    }
}