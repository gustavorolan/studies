package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.mapper.VideoMapper;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.model.VideoType;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllVideosServiceTest {
    @InjectMocks
    private GetAllVideosService getAllVideosService;

    @Mock
    private VideoMapper videoMapper;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Test
    @DisplayName("Should get all common videos not in team")
    void getAllVideo() {
        String filter = "";
        Pageable pageable = Pageable.ofSize(1).withPage(0);
        Boolean active = true;
        VideoType type = VideoType.COURSE;

        Video video = VideoFactory.get();
        VideoResponse videoResponse = VideoFactory.getVideoResponse();

        Page<Video> page = new PageImpl<Video>(List.of(video));

        Page<VideoResponse> response = new PageImpl<VideoResponse>(List.of(videoResponse));

        Mockito.when(findVideoWithThrow.findByActiveAndTypeTWithException(active, pageable, type, filter))
                .thenReturn(page);

        Mockito.when(videoMapper.toResponse(video))
                .thenReturn(videoResponse);

        Page<VideoResponse> value = getAllVideosService.getAllVideos(pageable, filter);

        Mockito.verify(findVideoWithThrow).findByActiveAndTypeTWithException(active, pageable, type, filter);
        Mockito.verify(videoMapper).toResponse(video);

        Assertions.assertEquals(response, value);
    }
}
