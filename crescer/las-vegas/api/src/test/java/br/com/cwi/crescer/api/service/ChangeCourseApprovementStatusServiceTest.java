package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChangeCourseApprovementStatusServiceTest {

    @InjectMocks
    private ChangeCourseApprovementStatusService changeCourseApprovementStatusService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Captor
    private ArgumentCaptor<Course> value;

    @Test
    @DisplayName("Should change course approval status")
    void changeCourseApprovementStatus() {
        Long id = 1L;
        Course course = CourseFactory.getCourseBuilder()
                .approvementStatus(true)
                .build();

        Course courseExpected = CourseFactory.getCourseBuilder()
                        .approvementStatus(false).build();

        Mockito.when(findCourseWithThrow.findByIdWithException(id))
                .thenReturn(course);

        ResponseMessage responseMessage = changeCourseApprovementStatusService.changeCourseApprovementStatus(id);

        Mockito.verify(findCourseWithThrow).findByIdWithException(id);
        Mockito.verify(courseRepository).save(value.capture());

        Course value = this.value.getValue();

        Assertions.assertEquals(courseExpected,value);
    }
}