package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.request.EditVideoRequest;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.service.vimeo.VimeoEditVideoService;
import br.com.cwi.crescer.api.util.VideoFactory;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EditVideoServiceTest {

    @InjectMocks
    private EditVideoService editVideoService;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private VimeoEditVideoService vimeoEditVideoService;

    @Mock
    private IsUserLoggedValidator isUserLoggedValidator;

    @Mock
    private VideoRepository videoRepository;

    @Captor
    private ArgumentCaptor<Video> captor;

    @Test
    @DisplayName("Should edit a video")
    void put() {
        Long id = 1L;
        Video video = VideoFactory.get();
        String newName = "newName";
        String newDesc= "newDesc";
        EditVideoRequest request = EditVideoRequest.builder()
                .name(newName)
                .desc(newDesc)
                .build();
        ResponseMessage response = ResponseMessage.builder()
                .response("You have updated successfully").build();


        Video finalVideo = VideoFactory.getBuilder()
                .name(newName)
                .description(newDesc)
                .build();

        Mockito.when(findVideoWithThrow.findByIdAndActiveWithException(id,true)).thenReturn(video);

        ResponseMessage result = editVideoService.put(id, request);

        Mockito.verify(findVideoWithThrow).findByIdAndActiveWithException(id,true);
        Mockito.verify(isUserLoggedValidator).verify(video.getAuthor());
        Mockito.verify(vimeoEditVideoService).edit(video.getEndPoint(), request.getName(), request.getDesc() );
        Mockito.verify(videoRepository).save(captor.capture());
        Video value = captor.getValue();

        Assertions.assertEquals(response,result);
        Assertions.assertEquals(finalVideo,value);

    }
}