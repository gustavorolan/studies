package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.mapper.VideoMapper;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetDetailedVideoService {

    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private VideoMapper videoMapper;

    public VideoResponse getDetailedVideo(Long videoId) {

        Video video = findVideoWithThrow.findByIdWithException(videoId);

        return videoMapper.toResponse(video);
    }
}
