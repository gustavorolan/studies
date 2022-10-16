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
public class IncludeCourseInTeamService {

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;
    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    @Autowired
    private PermissionValidator permissionValidator;

    @Autowired
    private CourseTeamConstraintService courseTeamConstraintService;


    public ResponseMessage includeCourseInTeam(Long courseId) {
        UserAccount loggedUser = userAccountAuthenticatedService.get();

        permissionValidator.validateManagerPermission(loggedUser);

        Course course = findCourseWithThrow.findByIdAndActiveWithException(courseId, true);

        Team team = loggedUser.getTeam();

        permissionValidator.validateLoggedUserPermission( team.getCreator());

        courseTeamConstraintService.verifyIfCourseIsNotInTeam(team, course);

        team.getCourses().add(course);
        course.getTeams().add(team);

        courseRepository.save(course);

        return ResponseMessage.builder().response("You've included successfully").build();
    }
}
