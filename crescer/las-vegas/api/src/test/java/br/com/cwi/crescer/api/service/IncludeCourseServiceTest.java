package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.finder.FindAttachmentWithThrow;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.util.UserAccountFactory;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;

@ExtendWith(MockitoExtension.class)
class IncludeCourseServiceTest {
    @InjectMocks
    private  IncludeCourseService includeCourseService;

    @Mock
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Mock
    private FindAttachmentWithThrow findAttachmentWithThrow;

    @Captor
    private ArgumentCaptor<Course> argumentCaptor;

    @Test
    @DisplayName("Should include a course")
    void includeCourse() {
        UserAccount userAccount = UserAccountFactory.get();
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        Course course = Course.builder()
                .id(1L).build();

        Course courseSaved = CourseFactory.getCourseBuilder()
                .author(userAccount)
                .image(attachment)
                .dateTimeCreation(LocalDateTime.now())
                .active(true)
                .assessment(SCORE.getScore())
                .approvementStatus(false)
                .build();

        CourseResponse courseResponse = CourseFactory.getCourseBuilderResponse()
                .image(attachmentResponseData)
                .active(true)
                .assessment(SCORE.getScore())
                .approvementStatus(false)
                .build();

        CourseRequest courseRequest = CourseFactory.getCourseRequest();

        Mockito.when(userAccountAuthenticatedService.get()).thenReturn(userAccount);
        Mockito.when(findAttachmentWithThrow.findByIdWithException(attachment.getId()))
                .thenReturn(attachment);
        Mockito.when(courseMapper.toEntity(courseRequest)).thenReturn(course);
        Mockito.when(courseMapper.toResponse(course)).thenReturn(courseResponse);

        CourseResponse result = includeCourseService.includeCourse(courseRequest, attachment.getId());

        Mockito.verify(parametersRegexValidator).verifyIfImageIdIsValid(attachment.getId());
        Mockito.verify(userAccountAuthenticatedService).get();
        Mockito.verify(findAttachmentWithThrow).findByIdWithException(attachment.getId());
        Mockito.verify(courseMapper).toEntity(courseRequest);
        Mockito.verify(courseMapper).toResponse(course);
        Mockito.verify(courseRepository).save(argumentCaptor.capture());

        Assertions.assertEquals(courseSaved,argumentCaptor.getValue());
        Assertions.assertEquals(courseResponse,result);

    }
}