package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class VideoCourseConstraintServiceTest {
    @InjectMocks
    private VideoCourseConstraintService videoCourseConstraintService;

    @Test
    @DisplayName("Should return if a video is already in course")
    void verifyIfVideoIsAlreadyInCourse() {
        Video video = VideoFactory.get();
        Course course = CourseFactory.getCourseBuilder().videos(List.of(video)).build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            videoCourseConstraintService.verifyIfVideoIsAlreadyInCourse(video,course);
        });


        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Video is already included in course\"", exception.getMessage());

    }

    @Test
    @DisplayName("Should return if a video is not in course")
    void verifyIfVideoIsNotInCourse() {
        Video video = VideoFactory.get();
        Course course = CourseFactory.getCourseBuilder().build();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            videoCourseConstraintService.verifyIfVideoIsNotInCourse(video,course);
        });


        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Video is not already included in course\"", exception.getMessage());
    }


}