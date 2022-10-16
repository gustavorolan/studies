package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.DateAndTimeFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ChangeCourseActiveStatusServiceTest {
    @InjectMocks
    private  ChangeCourseActiveStatusService changeCommentActiveStatus;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Mock
    private LocalDateAndTimeService localDateAndTimeService;

    @Mock
    private PermissionValidator permissionValidator;

    @Captor
    private ArgumentCaptor<Course> captor;

    @Test
    @DisplayName("Should change Course active status")
    void changeCourseActiveStatus() {
        LocalDateTime dateTime = DateAndTimeFactory.getDateTime();
        Long id = 1L;

        Course course = CourseFactory.getCourse();
        Course courseFinal = CourseFactory.getCourseBuilder()
                .active(true)
                .dateTimeUpdate(dateTime)
                .build();

        ResponseMessage response = ResponseMessage.builder().response("You've changed successfully").build();

        Mockito.when(localDateAndTimeService.getLocalDateTime()).thenReturn(dateTime);
        Mockito.when(findCourseWithThrow.findByIdAndActiveWithException(id, true)).thenReturn(course);

        ResponseMessage result = changeCommentActiveStatus.changeCourseActiveStatus(id);

        Mockito.verify(localDateAndTimeService).getLocalDateTime();
        Mockito.verify(findCourseWithThrow).findByIdAndActiveWithException(id, true);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(course.getAuthor());
        Mockito.verify(courseRepository).save(captor.capture());

        Course value = captor.getValue();

        Assertions.assertEquals(courseFinal,value);
        Assertions.assertEquals(response,result);
    }
}