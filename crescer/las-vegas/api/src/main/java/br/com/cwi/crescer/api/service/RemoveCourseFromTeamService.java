package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveCourseFromTeamService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTeamConstraintService courseTeamConstraintService;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    public ResponseMessage removeCourseFromTeam(Long courseId) {
        UserAccount loggedUser = userAccountAuthenticatedService.get();

        permissionValidator.validateManagerPermission(loggedUser);

        Course course = findCourseWithThrow.findByIdAndActiveWithException(courseId, true);

        Team team = userAccountAuthenticatedService.get().getTeam();

        permissionValidator.validateLoggedUserPermission(team.getCreator());

        courseTeamConstraintService.verifyIfCourseIsInTeam(team, course);

        team.getCourses().remove(course);
        course.getTeams().remove(team);

        courseRepository.save(course);

        return ResponseMessage.builder().response("You've removed successfully").build();
    }
}
