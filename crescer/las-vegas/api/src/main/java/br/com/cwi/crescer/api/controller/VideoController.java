package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.AssessmentRequest;
import br.com.cwi.crescer.api.controller.request.EditVideoRequest;
import br.com.cwi.crescer.api.controller.response.CourseResponse;
import br.com.cwi.crescer.api.controller.response.EvaluationResponse;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private EditVideoService editVideoService;

    @Autowired
    private GetMaterialFromVideoService getMaterialFromVideoService;

    @Autowired
    private RemoveVideoService removeVideoService;

    @Autowired
    private GetVideoService getVideoService;

    @Autowired
    private SupportMaterialService supportMaterialService;

    @Autowired
    private UploadVideoService uploadVideoService;

    @Autowired
    private IncludeVideoIntoCourseService includeVideoIntoCourseService;

    @Autowired
    private GetAllVideosService getAllVideosService;

    @Autowired
    private VideoAssesService videoAssesService;

    @Autowired
    private GetAllVideoEvaluationsService getAllVideoEvaluationsService;

    @Autowired
    private ChangeAssesActiveStatusService changeAssesActiveStatusService;

    @Autowired
    private GetDetailedVideoService getDetailedVideoService;

    @PutMapping("/{id}")
    public ResponseMessage editVideo(
            @Valid  @PathVariable Long id,
            @RequestBody EditVideoRequest request) {
        return editVideoService.put(id,request);
    }

    @GetMapping("/{id}/materialSupport")
    public ResponseEntity<Resource> downloadFile(@Valid  @PathVariable Long id ) {
        return getMaterialFromVideoService.download(id);
    }

    @PostMapping("/removeVideo/{id}")
    public ResponseMessage delete(@Valid  @PathVariable Long id ){
        return removeVideoService.remove(id);
    }

    @GetMapping("/{id}")
    public VideoResponse getVideo (@Valid  @PathVariable Long id ) {
        return getVideoService.getVideo(id);
    }

    @GetMapping("/list")
    public Page<VideoResponse> getAllVideos(Pageable pageable, @RequestParam String filter) {
        return getAllVideosService.getAllVideos(pageable, filter);
    }

    @GetMapping("/listShortVideos")
    public Page<VideoResponse> getAllShortVideos(Pageable pageable, @RequestParam String filter) {
        return getAllVideosService.getAllShortVideos(pageable, filter);
    }

    @PostMapping("/{videoId}/supportMaterial")
    public ResponseMessage uploadSupportMaterial(
            @RequestParam("supportMaterial") MultipartFile supportMaterial,
             @PathVariable Long videoId ) {
        return supportMaterialService.uploadSupportMaterial(supportMaterial,videoId);
    }

    @PostMapping("/upload")
    public ResponseMessage includeVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name ,
            @RequestParam("desc") String desc ) {
        return uploadVideoService.uploadVideo(file,name,desc);
    }

    @PostMapping("/includeVideoIntoCourse/{courseId}/{videoId}")
    public CourseResponse includeVideoIntoCourse(@PathVariable Long courseId, @PathVariable Long videoId) {
        return includeVideoIntoCourseService.includeVideoIntoCourse(courseId, videoId);
    }

    @PostMapping("/{id}/assessment")
    public ResponseMessage toAsses(@PathVariable Long id,@RequestBody AssessmentRequest request){
        return videoAssesService.toAsses(id, request.getComment(), request.getScore());
    }

    @PostMapping("/changeAssesStatus/assessment/{evaluationId}")
    public void changeAssesActiveStatusService(@PathVariable Long evaluationId){
        changeAssesActiveStatusService.changeAssesActiveStatus(evaluationId);
    }

    @GetMapping("/listVideoEvaluations/{videoId}")
    public Page<EvaluationResponse> getAllVideoEvaluations(Pageable pageable, @PathVariable Long videoId
            , @RequestParam String filter,  @RequestParam String score
            , @RequestParam String ordenation) {
        return getAllVideoEvaluationsService.getAllVideoEvaluations(pageable, videoId, filter, score, ordenation);
    }

    @GetMapping("/detailedVideo/{videoId}")
    public VideoResponse getDetailedVideo(@PathVariable Long videoId) {
        return getDetailedVideoService.getDetailedVideo(videoId);
    }
}
