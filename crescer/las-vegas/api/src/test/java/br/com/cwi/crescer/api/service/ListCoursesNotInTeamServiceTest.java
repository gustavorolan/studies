package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseListMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListCoursesNotInTeamServiceTest {

    @InjectMocks
    private ListCoursesNotInTeamService listCoursesNotInTeamService;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Mock
    private CourseListMapper courseMapper;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Test
    @DisplayName("Should list courses not included in team")
    void listCoursesNotInTeam() {
        Team team = TeamFactory.get();
        team.setCourses(new ArrayList<>());

        UserAccount user = UserAccountFactory.getBuilder().team(team).build();

        Course course = CourseFactory.getCourse();

        CourseResponse courseResponse = CourseFactory.getCourseResponse();

        Mockito.when(userAccountAuthenticatedService.get())
                .thenReturn(user);

        Boolean status = true;

        Mockito.when(findCourseWithThrow.findByApprovementStatusNotInTeamWithException(status, status, List.of()))
                .thenReturn(List.of(course));

        Mockito.when(courseMapper.toResponseList(List.of(course)))
                .thenReturn(List.of(courseResponse));

        listCoursesNotInTeamService.listCoursesNotInTeam();

        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findCourseWithThrow).findByApprovementStatusNotInTeamWithException(status, status, List.of());
        Mockito.verify(courseMapper).toResponseList(List.of(course));
    }
}
