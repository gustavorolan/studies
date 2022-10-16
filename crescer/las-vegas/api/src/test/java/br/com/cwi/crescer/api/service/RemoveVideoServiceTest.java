package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.service.vimeo.VimeoRemoveVideoService;
import br.com.cwi.crescer.api.util.VideoFactory;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RemoveVideoServiceTest {

    @InjectMocks
    private RemoveVideoService removeVideoService;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private VimeoRemoveVideoService vimeoRemoveVideoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private IsUserLoggedValidator isUserLoggedValidator;

    @Test
    void remove() {
        Long id=1L;
        Video video = VideoFactory.get();
        ResponseMessage response = ResponseMessage.builder().response("You have removed successfully").build();

        Mockito.when(findVideoWithThrow.findByIdWithException(id)).thenReturn(video);

        ResponseMessage result = removeVideoService.remove(id);

        Mockito.verify(findVideoWithThrow).findByIdWithException(id);
        Mockito.verify(isUserLoggedValidator).verify(video.getAuthor());
        Mockito.verify(vimeoRemoveVideoService).remove(video.getEndPoint());
        Mockito.verify(videoRepository).delete(video);

        Assertions.assertEquals(response,result);

    }
}