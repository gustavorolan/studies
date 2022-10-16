package br.com.cwi.crescer.api.service.vimeo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VideoRemoveVideoServiceTest {
    @InjectMocks
    private VimeoRemoveVideoService vimeoRemoveVideoService;

    @Test
    @DisplayName("Should remove video using video service")
    void remove() {
        String videoEndPoint = "/videos/707699077";

        vimeoRemoveVideoService.remove(videoEndPoint);
    }
}
