package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseTeamConstraintService {
    public void verifyIfCourseIsNotInTeam(Team team, Course course) {
        if(team.getCourses().contains(course)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Course is already included in team");
        }
    }

    public void verifyIfCourseIsInTeam(Team team, Course course) {
        if(!team.getCourses().contains(course)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Course is not included in team");
        }
    }
}
