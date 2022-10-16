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
class GetVideoServiceTest {

    @InjectMocks
    private GetVideoService getVideoService;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private VideoMapper videoMapper;

    @Test
    @DisplayName("Should return a video")
    void getVideo() {
        Video video = VideoFactory.get();
        VideoResponse videoResponse = VideoFactory.getVideoResponse();
        Long id = 1L;

        Mockito.when(findVideoWithThrow.findByIdAndActiveWithException(id,true)).thenReturn(video);
        Mockito.when(videoMapper.toResponse(video)).thenReturn(videoResponse);

        VideoResponse result = getVideoService.getVideo(id);

        Mockito.verify(findVideoWithThrow).findByIdAndActiveWithException(id,true);
        Mockito.verify(videoMapper).toResponse(video);

        Assertions.assertEquals(videoResponse,result);

    }
}