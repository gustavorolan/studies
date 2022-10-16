package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {
    @InjectMocks
    private CourseMapper courseMapper;

    @Mock
    private AttachmentMapper attachmentMapper;


    @Test
    @DisplayName("Should return a courseResponse")
    void toResponse() {
        UserAccount userAccount = UserAccountFactory.get();
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        Course course = CourseFactory.getCourseBuilder()
                .author(userAccount)
                .image(attachment)
                .build();

        CourseResponse courseResponse = CourseFactory.getCourseBuilderResponse()
                .image(attachmentResponseData)
                .authorId(userAccount.getId())
                .build();


        Mockito.when(attachmentMapper.toResponseAttachment(course.getImage())).thenReturn(attachmentResponseData);

        CourseResponse result = courseMapper.toResponse(course);

        Mockito.verify(attachmentMapper).toResponseAttachment(course.getImage());

        Assertions.assertEquals(courseResponse,result);
    }

    @Test
    @DisplayName("Should return a courseResponse")
    void toEntity() {
        Course course = CourseFactory.getCourseBuilder()
                .id(null)
                .build();

        CourseRequest request = CourseFactory.getCourseRequest();

        Course result = courseMapper.toEntity(request);

        Assertions.assertEquals(course,result);
    }
}