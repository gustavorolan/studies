package br.com.cwi.crescer.api.mapper;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class VideoMapperTest {
    @InjectMocks
    private VideoMapper videoMapper;

    @Test
    @DisplayName("Should return VideoResponse")
    void toResponse() {
        Video video = VideoFactory.getBuilder()
                .endPoint(null)
                .build();

        VideoResponse videoResponse = VideoFactory.getVideoResponse();

        VideoResponse result = videoMapper.toResponse(video);

        Assertions.assertEquals(videoResponse,result);
    }
}