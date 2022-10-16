package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.security.model.UserAccount;
import br.com.cwi.crescer.api.security.service.UserAccountAuthenticatedService;
import br.com.cwi.crescer.api.service.vimeo.VimeoEditVideoService;
import br.com.cwi.crescer.api.service.vimeo.VimeoUploadVideoService;
import br.com.cwi.crescer.api.validator.VideoIsTooLongValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

import static br.com.cwi.crescer.api.model.InitialScore.SCORE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class UploadVideoService {
    @Autowired
    private VimeoUploadVideoService vimeoUploadVideoService;

    @Autowired
    private VimeoEditVideoService vimeoEditVideoService;

    @Autowired
    private VideoIsTooLongValidator videoIsTooLongValidator;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserAccountAuthenticatedService userAccountAuthenticatedService;

    @Autowired
    private UploadVideoInfoService uploadVideoInfoService;


    public ResponseMessage uploadVideo (MultipartFile file, String name, String desc)  {

    try {

    videoIsTooLongValidator.verify(file.getInputStream(), VideoType.COURSE.getTimeMax());

    String endPoint = vimeoUploadVideoService.upload(file, name, desc);
    UserAccount userAccount = userAccountAuthenticatedService.get();
    
    String link = uploadVideoInfoService.getPlayerEmbedUrl(endPoint);

    Video video = Video.builder()
            .author(userAccount)
            .description(desc)
            .endPoint(endPoint)
            .name(name)
            .link(link)
            .type(VideoType.COURSE)
            .score(SCORE.getScore())
            .active(true)
            .build();

    videoRepository.save(video);

    return ResponseMessage.builder().response("You've successfully uploaded").build();
        } catch (IOException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());

        }

    }


}
