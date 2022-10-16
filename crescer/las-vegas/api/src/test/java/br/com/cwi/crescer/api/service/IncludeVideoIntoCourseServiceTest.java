package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
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
class IncludeVideoIntoCourseServiceTest {
    @InjectMocks
    private IncludeVideoIntoCourseService includeVideoIntoCourseService;

    @Mock
    private VideoRepository videoRepository;

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
    private ArgumentCaptor<Video> argumentCaptor;

    @Test
    @DisplayName("Should include video into some course")
    void includeVideoIntoCourse() {
        Course course = CourseFactory.getCourseBuilder()
                .videos(new ArrayList<>())
                .build();
        Video video = VideoFactory.get();

        Video videoSaved = VideoFactory.getBuilder().course(course).build();

        CourseResponse courseResponse = CourseFactory.getCourseBuilderResponse()
                .build();

        Mockito.when(findCourseWithThrow.findByIdWithException(course.getId()))
                .thenReturn(course);

        Mockito.when(findByIdWithException.findByIdWithException(video.getId())).thenReturn(video);


        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseResponse);

        CourseResponse result = includeVideoIntoCourseService
                .includeVideoIntoCourse(course.getId(), video.getId());

        Mockito.verify(findCourseWithThrow).findByIdWithException(course.getId());
        Mockito.verify(findByIdWithException).findByIdWithException(video.getId());
        Mockito.verify(courseMapper).toResponse(course);
        Mockito.verify(permissionValidator).validateLoggedUserPermission(course.getAuthor());
        Mockito.verify(videoCourseConstraintService).verifyIfVideoIsAlreadyInCourse(video, course);
        Mockito.verify(videoRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(videoSaved, argumentCaptor.getValue());
        Assertions.assertEquals(courseResponse, result);
    }
}