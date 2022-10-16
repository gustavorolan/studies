package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.EditVideoRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.service.vimeo.VimeoEditVideoService;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditVideoService {
    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private VimeoEditVideoService vimeoEditVideoService;

    @Autowired
    private IsUserLoggedValidator isUserLoggedValidator;

    @Autowired
    private VideoRepository videoRepository;

    public ResponseMessage put(Long id, EditVideoRequest request) {
        Video video = findVideoWithThrow.findByIdAndActiveWithException(id, true);
        isUserLoggedValidator.verify(video.getAuthor());
        if (request.getName().isBlank()) request.setName(video.getName());
        if (request.getDesc().isBlank()) request.setDesc(video.getDescription());
        vimeoEditVideoService.edit(video.getEndPoint(), request.getName(), request.getDesc() );
        videoRepository.save(video);
        return ResponseMessage.builder().response("You have updated successfully").build();
    }
}
