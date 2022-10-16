package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.model.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    @Autowired
    private AttachmentMapper attachmentMapper;

    public CourseResponse toResponse(Course course){
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .image(attachmentMapper.toResponseAttachment(course.getImage()))
                .active(course.getActive())
                .approvementStatus(course.getApprovementStatus())
                .authorId(course.getAuthor().getId())
                .assessment(course.getAssessment())
                .build();
    }



    public Course toEntity(CourseRequest request) {
        return new ModelMapper().map(request, Course.class);
    }
}
