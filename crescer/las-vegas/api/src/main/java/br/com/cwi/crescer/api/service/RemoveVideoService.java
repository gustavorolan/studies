package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.service.vimeo.VimeoRemoveVideoService;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveVideoService {
    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private VimeoRemoveVideoService vimeoRemoveVideoService;

    @Autowired
    private IsUserLoggedValidator isUserLoggedValidator;

    @Autowired
    private VideoRepository videoRepository;

    public ResponseMessage remove (Long id)  {
        Video video = findVideoWithThrow.findByIdWithException(id);
        isUserLoggedValidator.verify(video.getAuthor());
        vimeoRemoveVideoService.remove(video.getEndPoint());
        videoRepository.delete(video);
        return ResponseMessage.builder().response("You have removed successfully").build();
    }
}
