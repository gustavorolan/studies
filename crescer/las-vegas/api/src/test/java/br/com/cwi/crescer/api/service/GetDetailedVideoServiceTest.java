package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.mapper.VideoMapper;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.util.VideoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetDetailedVideoServiceTest {
    @InjectMocks
    private GetDetailedVideoService getDetailedVideoService;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private VideoMapper videoMapper;

    @Test
    @DisplayName("Should return detailed video")
    void getDetailedVideoService() {
        Long videoId = 1l;

        Video video = VideoFactory.get();

        VideoResponse videoResponse = VideoFactory.getVideoBuilderResponse().build();

        Mockito.when(findVideoWithThrow.findByIdWithException(videoId))
                .thenReturn(video);

        Mockito.when(videoMapper.toResponse(video))
                .thenReturn(videoResponse);

        VideoResponse response = getDetailedVideoService.getDetailedVideo(videoId);

        Mockito.verify(findVideoWithThrow).findByIdWithException(videoId);
        Mockito.verify(videoMapper).toResponse(video);

        Assertions.assertEquals(videoResponse, response);
    }
}
