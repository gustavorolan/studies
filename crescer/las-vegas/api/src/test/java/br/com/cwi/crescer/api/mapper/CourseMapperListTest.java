package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CourseMapperListTest {
    @InjectMocks
    private CourseListMapper courseListMapper;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private AttachmentMapper attachmentMapper;

    @Mock
    private VideoMapper videoMapper;

    @Test
    @DisplayName("Should return a list of courseResponse")
    void toResponse() throws IOException {
        Course course = CourseFactory.getCourse();

        CourseResponse courseResponse = CourseFactory.getCourseResponse();
        List<CourseResponse>courseResponseList = List.of(courseResponse);

        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseResponse);

        List<CourseResponse> result = courseListMapper.toResponseList(List.of(course));

        Mockito.verify(courseMapper).toResponse(course);

        Assertions.assertEquals(courseResponseList,result);
    }


    @Test
    @DisplayName("Should return a list of courseResponse with videoResponse")
    void toResponseWithVideos() {
        UserAccount userAccount = UserAccountFactory.get();
        VideoResponse videoResponse = VideoFactory.getVideoResponse();
        Video video = VideoFactory.get();
        Course course = CourseFactory.getCourseBuilder()
                .author(userAccount)
                .videos(List.of(video))
                .build();

        CourseCompleteResponse courseCompleteResponse = CourseFactory.getCourseCompleteResponse();

        Mockito.when(attachmentMapper.toResponseAttachment(null)).thenReturn(null);
        Mockito.when(videoMapper.toResponse(video)).thenReturn(videoResponse);

        CourseCompleteResponse result = courseListMapper.toResponseWithVideos(course);

        Mockito.verify(attachmentMapper).toResponseAttachment(null);
        Mockito.verify(videoMapper).toResponse(video);

        Assertions.assertEquals(courseCompleteResponse,result);

    }


}