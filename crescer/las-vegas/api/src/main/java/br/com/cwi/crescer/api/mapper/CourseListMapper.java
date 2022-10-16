package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseListMapper {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private VideoMapper videoMapper;

    public List<CourseResponse> toResponseList(List<Course> courses) {
        return courses.stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CourseCompleteResponse toResponseWithVideos(Course course) {
        return CourseCompleteResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .image(attachmentMapper.toResponseAttachment(course.getImage()))
                .active(course.getActive())
                .approvementStatus(course.getApprovementStatus())
                .dateTimeCreation(course.getDateTimeCreation())
                .dateTimeUpdate(course.getDateTimeUpdate())
                .authorId(course.getAuthor().getId())
                .authorName(course.getAuthor().getFullName())
                .videos(course.getVideos()
                        .stream()
                        .map(videoMapper::toResponse)
                        .collect(Collectors.toList()))
                .assessment(course.getAssessment())
                .build();
    }
}
