package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.model.Course;
import br.com.cwi.crescer.api.model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VideoCourseConstraintService {
    public void verifyIfVideoIsAlreadyInCourse(Video video, Course course) {
        if(course.getVideos().contains(video)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Video is already included in course");
        }
    }

    public void verifyIfVideoIsNotInCourse(Video video, Course course) {
        if(!course.getVideos().contains(video)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Video is not already included in course");
        }
    }
}
