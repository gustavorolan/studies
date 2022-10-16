package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Team;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.TeamFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CourseTeamConstraintServiceTest {

    @InjectMocks
    private  CourseTeamConstraintService courseTeamConstraintService;

    @Test
    @DisplayName("Should throw a exception if  course is not in team")
    void testVerifyIfCourseIsAlreadyInTeam() {

        Course course = CourseFactory.getCourse();
        Team team = TeamFactory.getBuilder().courses(List.of(course)).build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            courseTeamConstraintService.verifyIfCourseIsNotInTeam(team,course);
        });

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Course is already included in team\"", exception.getMessage());

    }

    @Test
    @DisplayName("Should throw a exception if  course is already in team")
    void testVerifyIfCourseIsNotAlreadyInTeam() {

        Course course = CourseFactory.getCourse();
        Team team = TeamFactory.getBuilder().courses(new ArrayList<>()).build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            courseTeamConstraintService.verifyIfCourseIsInTeam(team,course);
        });

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Course is not included in team\"", exception.getMessage());

    }
}