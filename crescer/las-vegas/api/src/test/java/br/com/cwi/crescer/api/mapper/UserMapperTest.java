package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.RegisterUserRequest;
import br.com.cwi.crescer.api.controller.response.*;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
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
class UserMapperTest {
    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private TeamMapper teamMapper;

    @Test
    @DisplayName("Should return a UserResponse")
    void toResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();

        UserResponse result = userMapper.toResponse(userAccount);

        Assertions.assertEquals(userResponse,result);
    }

    @Test
    @DisplayName("Should return a UserResponse with Courses")
    void toResponseWithCourses() {
        Course course = CourseFactory.getCourse();
        CourseResponse courseResponse = CourseFactory.getCourseResponse();
        UserAccount userAccount = UserAccountFactory.getBuilder()
                .myCourses(List.of(course))
                .build();

        RegisteredCoursesResponse userResponse = UserAccountFactory.getBuilderUserWithCourses()
                .fullName("name")
                .assessment(5)
                .registeredCoursesResponseList(List.of(courseResponse))
                .build();

        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseResponse);

        RegisteredCoursesResponse result = userMapper.toResponseWithCourses(userAccount);

        Mockito.verify(courseMapper).toResponse(course);

        Assertions.assertEquals(userResponse,result);
    }

    @Test
    @DisplayName("Should return a UserResponseList")
    void testToResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        UserResponse userResponse = UserAccountFactory.getUserResponse();

        List<UserResponse> result = userMapper.toResponse(List.of(userAccount));

        Assertions.assertEquals(List.of(userResponse),result);
    }

    @Test
    @DisplayName("Should return a UserRequest")
    void toEntity() {
        UserAccount userAccount = UserAccountFactory.get();
        RegisterUserRequest userRequest = UserAccountFactory.getUserRequest();

        UserAccount result = userMapper.toEntity(userRequest);

        Assertions.assertEquals(userAccount.getPassword(),result.getPassword());
        Assertions.assertEquals(userAccount.getEmail(),result.getEmail());
        Assertions.assertEquals(userAccount.getFullName(),result.getFullName());

    }

    @Test
    @DisplayName("Should return a UserCompleteResponse")
    void toResponseWithCoursesAndTeams() {
        Course course = CourseFactory.getCourse();
        CourseResponse courseResponse = CourseFactory.getCourseResponse();
        Team team = TeamFactory.get();
        TeamResponse teamResponse = TeamFactory.getTeamResponse();

        UserAccount userAccount = UserAccountFactory.getBuilder()
                .myCourses(List.of(course))
                .createdTeam(team)
                .createdCourses(List.of(course))
                .build();

        UserCompleteResponse userResponse = UserAccountFactory.getBuilderUserCompleteResponse()
                .fullName("name")
                .assessment(5)
                .courses(List.of(courseResponse))
                .build();

        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseResponse);

        UserCompleteResponse result = userMapper.toResponseWithCoursesAndTeams(userAccount);

        Mockito.verify(courseMapper).toResponse(course);

        Assertions.assertEquals(userResponse,result);
    }
}