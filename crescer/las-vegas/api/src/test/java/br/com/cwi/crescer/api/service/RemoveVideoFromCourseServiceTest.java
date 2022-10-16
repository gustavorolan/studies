package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class RemoveVideoFromCourseServiceTest {

    @InjectMocks
    private RemoveVideoFromCourseService removeVideoFromCourseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private PermissionValidator permissionValidator;

    @Mock
    private VideoCourseConstraintService videoCourseConstraintService;

    @Mock
    private FindVideoWithThrow findByIdWithException;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Captor
    private ArgumentCaptor<Course> argumentCaptor;


    @Test
    @DisplayName("Should remove video from service")
    void removeVideoFromCourse() {
        Video video = VideoFactory.get();
        ArrayList<Video> videoArrayList = new ArrayList<>();
        videoArrayList.add(video);
        Course course = CourseFactory.getCourseBuilder().videos(videoArrayList).build();
        Course courseSaved = CourseFactory.getCourseBuilder().videos(new ArrayList<>()).build();
        CourseResponse courseMapped = CourseFactory.getCourseResponse();

        Mockito.when(findCourseWithThrow.findByIdWithException(course.getId()))
                .thenReturn(course);
        Mockito.when(findByIdWithException.findByIdWithException(video.getId()))
                .thenReturn(video);
        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseMapped);

        CourseResponse courseResponse = removeVideoFromCourseService
                .removeVideoFromCourse(course.getId(), video.getId());

        Mockito.verify(findCourseWithThrow).findByIdWithException(course.getId());
        Mockito.verify(findByIdWithException).findByIdWithException(video.getId());
        Mockito.verify(permissionValidator).validateLoggedUserPermission(course.getAuthor());
        Mockito.verify(videoCourseConstraintService).verifyIfVideoIsNotInCourse(video, course);
        Mockito.verify(courseMapper).toResponse(course);
        Mockito.verify(courseRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(courseSaved, argumentCaptor.getValue());
        Assertions.assertEquals(courseMapped, courseResponse);
    }
}