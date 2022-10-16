package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.UpdateCourseRequest;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.mapper.CourseMapper;
import br.com.cwi.crescer.api.model.Attachment;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.repository.CourseRepository;
import br.com.cwi.crescer.api.service.finder.FindCourseWithThrow;
import br.com.cwi.crescer.api.validator.PermissionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VerifyImageParameterService verifyImageParameterService;

    @Autowired
    private FindCourseWithThrow findCourseWithThrow;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private PermissionValidator permissionValidator;

    public CourseResponse update(UpdateCourseRequest request, Long courseId, String imageId) {
        Course course = findCourseWithThrow.findByIdAndActiveWithException(courseId, true);

        permissionValidator.validateLoggedUserPermission(course.getAuthor());

        Attachment attachment = verifyImageParameterService.verifyImageParameter(imageId);

        if (request.getName().isBlank())course.setName(request.getName());
        if (request.getDescription().isBlank()) course.setDescription( request.getDescription());
        if (attachment!=null)course.setImage(attachment);

        courseRepository.save(course);

        return courseMapper.toResponse(course);
    }
}
