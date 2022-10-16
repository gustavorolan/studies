package br.com.cwi.crescer.api.service.finder;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FindCourseWithThrow {
    @Autowired
    private CourseRepository courseRepository;

    private static final String RESPONSE = "Course does not exist";

    public Course findByIdWithException(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public Course findByIdAndActiveWithException(Long id, Boolean active) {
        return courseRepository.findByIdAndActive(id, active)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public List<Course> findByApprovementStatusWithException(Boolean active, Boolean approvementStatus) {
        return courseRepository.findByActiveAndApprovementStatus(active, approvementStatus)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }

    public List<Course> findByApprovementStatusNotInTeamWithException(Boolean active, Boolean approvementStatus, List<Long> ids) {
        return courseRepository.findByActiveAndApprovementStatusAndIdNotIn(active, approvementStatus, ids)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, RESPONSE));
    }
}
