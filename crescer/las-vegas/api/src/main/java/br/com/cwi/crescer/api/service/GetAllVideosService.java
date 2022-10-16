package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.mapper.VideoMapper;
import br.com.cwi.crescer.api.model.VideoType;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllVideosService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private FindVideoWithThrow findVideoWithThrow;

    public Page<VideoResponse> getAllVideos(Pageable pageable, String filter) {

        return findVideoWithThrow.findByActiveAndTypeTWithException(true, pageable, VideoType.COURSE, filter)
                .map(video -> videoMapper.toResponse(video));
    }

    public Page<VideoResponse> getAllShortVideos(Pageable pageable, String filter) {

        return findVideoWithThrow.findByActiveAndTypeTWithException(true, pageable, VideoType.REELS, filter)
                .map(video -> videoMapper.toResponse(video));
    }
}
