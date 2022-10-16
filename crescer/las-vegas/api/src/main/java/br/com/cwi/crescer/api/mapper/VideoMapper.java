package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.model.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public VideoResponse toResponse(Video video
    ){
        return VideoResponse.builder()
                .id(video.getId())
                .name(video.getName())
                .description(video.getDescription())
                .vimeoResponse(video.getEndPoint())
                .link(video.getLink())
                .score(video.getScore())
                .build();
    }
}
