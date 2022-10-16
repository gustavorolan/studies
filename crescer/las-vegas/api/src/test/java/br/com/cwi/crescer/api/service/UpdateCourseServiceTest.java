package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.UpdateCourseRequest;
import br.com.cwi.crescer.api.controller.response.AttachmentResponseData;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.util.AttachmentFactory;
import br.com.cwi.crescer.api.util.CourseFactory;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCourseServiceTest {

    @InjectMocks
    private UpdateCourseService updateCourseService;


    @Mock
    private CourseRepository courseRepository;

    @Mock
    private VerifyImageParameterService verifyImageParameterService;

    @Mock
    private FindCourseWithThrow findCourseWithThrow;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private PermissionValidator permissionValidator;

    @Captor
    private ArgumentCaptor<Course> courseArgumentCaptor;

    @Test
    @DisplayName("Should update course")
    void update() {
        Course course = CourseFactory.getCourse();
        Attachment attachment = AttachmentFactory.getAttachment();
        AttachmentResponseData attachmentResponseData = AttachmentFactory.getAttachmentResponseData();
        UpdateCourseRequest request = UpdateCourseRequest.builder()
                .name("course")
                .description("new description")
                .build();
        CourseResponse courseResponse = CourseFactory.getCourseBuilderResponse().build();
        Course courseSaved = CourseFactory.getCourseBuilder()
                .name("course")
                .description("new description")
                .image(attachment).build();

        CourseResponse response = CourseResponse.builder().id(1L)
                .name("name")
                .description("desc")
                .active(true)
                .approvementStatus(null)
                .image(null)
                .authorId(null)
                .assessment(5)
                .build();


        Mockito.when(findCourseWithThrow.findByIdAndActiveWithException(course.getId(),true))
                .thenReturn(course);
        Mockito.when(verifyImageParameterService.verifyImageParameter(attachment.getId()))
                .thenReturn(attachment);
        Mockito.when(courseMapper.toResponse(course))
                .thenReturn(courseResponse);

        CourseResponse update = updateCourseService.update(request, course.getId(), attachment.getId());

        Mockito.verify(findCourseWithThrow).findByIdAndActiveWithException(course.getId(),true);
        Mockito.verify(verifyImageParameterService).verifyImageParameter(attachment.getId());
        Mockito.verify(permissionValidator).validateLoggedUserPermission(course.getAuthor());
        Mockito.verify(courseMapper).toResponse(course);
        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());

        Assertions.assertEquals(courseSaved,courseArgumentCaptor.getValue());

        Assertions.assertEquals(response,update);
    }


}