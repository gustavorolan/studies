package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.util.CourseFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FindCourseWithThrowTest {

    @InjectMocks
    private FindCourseWithThrow findCourseWithThrow;

    @Mock
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Should find course correctly")
    void findById() {
        Long id = 1L;

        Course course = CourseFactory.getCourse();

        Mockito.when(courseRepository.findById(id)).thenReturn(Optional.ofNullable(course));

        Course value = findCourseWithThrow.findByIdWithException(id);

        Mockito.verify(courseRepository).findById(id);

        Assertions.assertEquals(course, value);
    }

    @Test
    @DisplayName("Should throw an exception, because course was not found")
    void findByIdWithException() {
        Long id = 1L;

        Mockito.when(courseRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCourseWithThrow.findByIdWithException(id);
        });

        Mockito.verify(courseRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"Course does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find active course correctly")
    void findByIdAndActive() {
        Long id = 1L;

        Boolean active = true;

        Course course = CourseFactory.getCourse();

        Mockito.when(courseRepository.findByIdAndActive(id, active)).thenReturn(Optional.ofNullable(course));

        Course value = findCourseWithThrow.findByIdAndActiveWithException(id, active);

        Mockito.verify(courseRepository).findByIdAndActive(id, active);

        Assertions.assertEquals(course, value);
    }

    @Test
    @DisplayName("Should throw an exception, because course was not found, by active")
    void findByIdAndActiveWithException() {
        Long id = 1L;
        boolean active = true;

        Mockito.when(courseRepository.findByIdAndActive(id,active)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCourseWithThrow.findByIdAndActiveWithException(id,active);
        });

        Mockito.verify(courseRepository).findByIdAndActive(id,active);

        Assertions.assertEquals("404 NOT_FOUND \"Course does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find active courses correctly by approbement status")
    void findByApprovementStatusWith() {
        Boolean courseApprovementStatus = true;
        Boolean courseActiveStatus = true;

        Course course = CourseFactory.getCourse();

        Mockito.when(courseRepository.findByActiveAndApprovementStatus(courseApprovementStatus, courseActiveStatus)).thenReturn(Optional.ofNullable(List.of(course)));

        List<Course> value = findCourseWithThrow.findByApprovementStatusWithException(courseApprovementStatus, courseActiveStatus);

        Mockito.verify(courseRepository).findByActiveAndApprovementStatus(courseActiveStatus, courseActiveStatus);

        Assertions.assertEquals(course, value.get(0));
    }

    @Test
    @DisplayName("Should throw an exception, because course was not found, by Approvement")
    void findByApprovementStatusWithException() {
        Long id = 1L;
        Boolean courseApprovementStatus = true;
        Boolean courseActiveStatus = true;

        Mockito.when(courseRepository.findByActiveAndApprovementStatus(courseActiveStatus, courseApprovementStatus)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            findCourseWithThrow.findByApprovementStatusWithException(courseActiveStatus, courseApprovementStatus);
        });

        Mockito.verify(courseRepository).findByActiveAndApprovementStatus(courseActiveStatus, courseApprovementStatus);

        Assertions.assertEquals("404 NOT_FOUND \"Course does not exist\"", exception.getMessage());
    }

    @Test
    @DisplayName("Should find active courses not in team correctly by approbement status")
    void findByApprovementStatusNotInTeam() {
        List<Long> ids = new ArrayList<>();
        Boolean courseApprovementStatus = true;
        Boolean courseActiveStatus = true;

        Course course = CourseFactory.getCourse();

        Mockito.when(courseRepository.findByActiveAndApprovementStatusAndIdNotIn(courseApprovementStatus, courseActiveStatus, ids))
                .thenReturn(Optional.ofNullable(List.of(course)));

        List<Course> value = findCourseWithThrow.findByApprovementStatusNotInTeamWithException(courseApprovementStatus, courseActiveStatus, ids);

        Mockito.verify(courseRepository).findByActiveAndApprovementStatusAndIdNotIn(courseActiveStatus, courseActiveStatus, ids);

        Assertions.assertEquals(course, value.get(0));
    }

    @Test
    @DisplayName("Should not find active courses not in team correctly by approbement status")
    void findByApprovementStatusNotInTeamWithError() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        Boolean courseApprovementStatus = true;
        Boolean courseActiveStatus = true;

        Mockito.when(courseRepository.findByActiveAndApprovementStatusAndIdNotIn(courseApprovementStatus, courseActiveStatus, ids))
                .thenReturn(Optional.ofNullable(List.of()));

        List<Course> value = findCourseWithThrow.findByApprovementStatusNotInTeamWithException(courseApprovementStatus, courseActiveStatus, ids);

        Mockito.verify(courseRepository).findByActiveAndApprovementStatusAndIdNotIn(courseActiveStatus, courseActiveStatus, ids);

        Assertions.assertEquals(List.of(), value);
    }
}