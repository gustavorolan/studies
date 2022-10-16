package br.com.cwi.crescer.api.service.vimeo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VimeoInfoVideoServiceTest {

    @InjectMocks
    private VimeoInfoVideoService vimeoInfoVideoService;

    @Test
    @DisplayName("Should remove course from team")
    void info() {
        String videoEnpPoint = "/videos/707699077";

        vimeoInfoVideoService.info(videoEnpPoint);
    }
}
