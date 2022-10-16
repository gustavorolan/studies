package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveVideoFromCourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoCourseConstraintService videoCourseConstraintService;
    @Autowired
    private PermissionValidator permissionValidator;


    @Autowired
    private FindVideoWithThrow findByIdWithException;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    public CourseResponse removeVideoFromCourse(Long courseId, Long videoId) {
        Course course = findCourseWithThrow.findByIdWithException(courseId);

        Video video = findByIdWithException.findByIdWithException(videoId);

        permissionValidator.validateLoggedUserPermission(course.getAuthor());

        videoCourseConstraintService.verifyIfVideoIsNotInCourse(video, course);

        video.setCourse(null);
        course.getVideos().remove(video);

        courseRepository.save(course);

        return courseMapper.toResponse(course);
    }
}