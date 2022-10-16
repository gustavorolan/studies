package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.mapper.VideoMapper;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetVideoService {
    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    @Autowired
    private VideoMapper videoMapper;

    public VideoResponse getVideo(Long id) {
        Video video = findVideoWithThrow.findByIdAndActiveWithException(id, true);

        return videoMapper.toResponse(video);
    }}