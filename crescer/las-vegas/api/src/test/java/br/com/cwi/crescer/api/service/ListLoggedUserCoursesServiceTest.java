package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListLoggedUserCoursesServiceTest {
    @InjectMocks
    private  ListLoggedUserCoursesService listLoggedUserCoursesService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private CourseListMapper courseMapper;

    @Test
    @DisplayName("Should return user's created courses list")
    void listLoggedUserCourses() {
        Course course = CourseFactory.getCourse();
        UserAccount user = UserAccountFactory.getBuilder().myCourses(List.of(course)).build();
        CourseResponse courseResponse = CourseFactory.getCourseResponse();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(user);
        Mockito.when(courseMapper.toResponseList(user.getCreatedCourses())).thenReturn(List.of(courseResponse));

        List<CourseResponse> courseResponseList = listLoggedUserCoursesService.listLoggedUserCourses();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(courseMapper).toResponseList(user.getCreatedCourses());

        Assertions.assertEquals(List.of(courseResponse),courseResponseList);

    }
}