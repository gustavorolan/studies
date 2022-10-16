package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class RemoveCourseFromTeamServiceTest {

    @InjectMocks
    private RemoveCourseFromTeamService removeCourseFromTeamService;

    @InjectMocks
    private UploadVideoInfoService uploadVideoInfoService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private CourseTeamConstraintService courseTeamConstraintService;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Test
    @DisplayName("Should remove course from team")
    void removeCourseFromTeam() {
        Long id = 1L;

        Boolean active = true;

        Team team = TeamFactory.getBuilder().courses(new ArrayList<>()).build();

        UserAccount user = UserAccountFactory.getBuilder().team(team).build();

        team.setCreator(user);

        Course course = CourseFactory.getCourseBuilder().teams(new ArrayList<>()).build();

        Mockito.when(findCourseWithThrow.findByIdAndActiveWithException(id, active))
                .thenReturn(course);

        Mockito.when(userAccountAuthenticatedService.get())
                .thenReturn(user);

        ResponseMessage response = removeCourseFromTeamService.removeCourseFromTeam(id);

        Mockito.verify(permissionValidator).validateManagerPermission(user);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(user);
        Mockito.verify(courseTeamConstraintService).verifyIfCourseIsInTeam(team, course);
        Mockito.verify(courseRepository).save(course);

        Assertions.assertEquals("You've removed successfully", response.getResponse());
    }
}
