package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.controller.response.UserCompleteResponse;
import br.com.cwi.crescer.api.mapper.UserMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.service.finder.FindUserWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetDetailedUserServiceTest {

    @InjectMocks
    private GetDetailedUserService getDetailedUserService;

    @Mock
    private FindUserWithThrow findUserWithThrow;

    @Mock
    private UserMapper userMapper;


    @Test
    void getDetailedUser() {
        Long id = 1L;
        Course course = CourseFactory.getCourse();
        CourseResponse courseResponse = CourseFactory.getCourseResponse();
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .myCourses(List.of(course)).build();

        UserCompleteResponse userResponse = UserAccountFactory.getBuilderUserCompleteResponse()
                .courses(List.of(courseResponse))
                .build();


        Mockito.when(findUserWithThrow.findByIdAndActiveWithException(id, true))
                .thenReturn(userAccount);
        Mockito.when(userMapper.toResponseWithCoursesAndTeams(userAccount))
                .thenReturn(userResponse);


        UserCompleteResponse result = getDetailedUserService.getDetailedUser(id);

        Mockito.verify(findUserWithThrow).findByIdAndActiveWithException(id, true);
        Mockito.verify(userMapper).toResponseWithCoursesAndTeams(userAccount);


        Assertions.assertEquals(userResponse,result);
    }
}