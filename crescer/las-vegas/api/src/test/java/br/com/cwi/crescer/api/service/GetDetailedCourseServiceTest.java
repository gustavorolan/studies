package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDetailedCourseServiceTest {
    @InjectMocks
    private GetDetailedCourseService getDetailedCourseService;

    @Mock
    private CourseListMapper courseMapper;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Test
    @DisplayName("Should return a course detailed")
    void getDetailedCourse() {
        Long teamId =1L;
        Course course = CourseFactory.getCourse();
        CourseCompleteResponse courseResponse = CourseFactory.getCourseCompleteResponse();

        Mockito.when(findCourseWithThrow.findByIdWithException(teamId)).thenReturn(course);
        Mockito.when(courseMapper.toResponseWithVideos(course)).thenReturn(courseResponse);

        CourseCompleteResponse detailedCourse = getDetailedCourseService.getDetailedCourse(teamId);

        Mockito.verify(findCourseWithThrow).findByIdWithException(teamId);
        Mockito.verify(courseMapper).toResponseWithVideos(course);

        Assertions.assertEquals(courseResponse,detailedCourse);
    }
}