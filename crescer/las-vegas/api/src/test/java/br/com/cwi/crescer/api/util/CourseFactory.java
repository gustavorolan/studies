package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;

import java.util.List;

public class CourseFactory {
    public static Course getCourse(){
        return getCourseBuilder().build();
    }


    public static Course.CourseBuilder getCourseBuilder(){
        return Course.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .active(true)
                .videos(List.of(new Video()))
                .assessment(5);
    }

    public static CourseRequest getCourseRequest(){
        return CourseRequest.builder()
                .description("desc")
                .name("name")
                .build();
    }

    public static CourseResponse.CourseResponseBuilder getCourseBuilderResponse(){
            return CourseResponse.builder()
                    .id(1L)
                    .name("name")
                    .description("desc")
                    .active(true)
                    .assessment(5);
    }

    public static CourseResponse getCourseResponse(){
        return getCourseBuilderResponse().build();
    }

    public static CourseCompleteResponse getCourseCompleteResponse(){
        return CourseCompleteResponse.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .active(true)
                .dateTimeCreation(null)
                .dateTimeUpdate(null)
                .authorId(1L)
                .authorName("name")
                .videos(List.of(VideoFactory.getVideoResponse()))
                .assessment(5)
                .build();
    }

}
