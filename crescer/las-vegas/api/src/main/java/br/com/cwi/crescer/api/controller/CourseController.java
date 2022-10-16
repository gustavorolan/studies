package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.CourseRequest;
import br.com.cwi.crescer.api.controller.request.IncludeUserIntoCourseRequest;
import br.com.cwi.crescer.api.controller.request.UpdateCourseRequest;
import br.com.cwi.crescer.api.controller.response.CourseCompleteResponse;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private IncludeCourseService includeCourseService;

    @Autowired
    private IncludeCourseInTeamService includeCourseInTeamService;

    @Autowired
    private ChangeCourseActiveStatusService changeCourseActiveStatusService;

    @Autowired
    private IncludeUserIntoCourse includeUserIntoCourse;

    @Autowired
    private UpdateCourseService updateCourseService;

    @Autowired
    private ListAllCoursesService listAllCoursesService;

    @Autowired
    private ListLoggedUserCoursesService listLoggedUserCoursesService;

    @Autowired
    private GetDetailedCourseService getDetailedCourseService;

    @Autowired
    private ChangeCourseApprovementStatusService changeCourseApprovementStatusService;

    @Autowired
    private ListPendentCoursesService listPendentCoursesService;

    @Autowired
    private ListCoursesNotInTeamService listCoursesNotInTeamService;

    @Autowired
    private RemoveCourseFromTeamService removeCourseFromTeamService;

    @GetMapping("/myCourses")
    public List<CourseResponse> listLoggedUserCourses() {
        return listLoggedUserCoursesService.listLoggedUserCourses();
    }

    @GetMapping("/allCourses")
    public List<CourseResponse> listAllCourses() {
        return listAllCoursesService.listAllCourses();
    }

    @GetMapping("/pendentCourses")
    public List<CourseResponse> listPendentCourses() {
        return listPendentCoursesService.listPendentCourses();
    }

    @GetMapping("/coursesNotInTeam")
    public List<CourseResponse> listCoursesNotInTeam() {
        return listCoursesNotInTeamService.listCoursesNotInTeam();
    }

    @GetMapping("/detail/{courseId}")
    public CourseCompleteResponse getDetailedCourse(@PathVariable Long courseId) {
        return getDetailedCourseService.getDetailedCourse(courseId);
    }

    @PostMapping("/include/{imageId}")
    public CourseResponse includeCourse(@Valid @RequestBody CourseRequest request, @PathVariable String imageId) {
        return includeCourseService.includeCourse(request, imageId);
    }

    @PostMapping("/includeCourseInTeam/{courseId}")
    public ResponseMessage includeCourseInTeam(@PathVariable Long courseId) {
        return includeCourseInTeamService.includeCourseInTeam(courseId);
    }

    @PostMapping("/removeCourseFromTeam/{courseId}")
    public ResponseMessage removeCourseFromTeam(@PathVariable Long courseId) {
        return removeCourseFromTeamService.removeCourseFromTeam(courseId);
    }

    @PostMapping("/changeApprovementStatus/{courseId}")
    public ResponseMessage changeCourseApprovementStatus(@PathVariable Long courseId) {
        return changeCourseApprovementStatusService.changeCourseApprovementStatus(courseId);
    }

    @PostMapping("/changeActiveStatus/{courseId}")
    public ResponseMessage changeCourseActiveStatus(@PathVariable Long courseId) {
        return changeCourseActiveStatusService.changeCourseActiveStatus(courseId);
    }

    @PostMapping("/update/{courseId}/{imageId}")
    public CourseResponse update(@Valid @RequestBody UpdateCourseRequest request, @PathVariable Long courseId, @PathVariable String imageId) {
        return updateCourseService.update(request, courseId, imageId);
    }

    @PutMapping("/{courseId}/includeUser")
    public ResponseMessage includePersonIntoCourse(@PathVariable Long courseId,@RequestBody IncludeUserIntoCourseRequest request ){
        return includeUserIntoCourse.include(courseId,request);
    }
}
