package br.com.cwi.crescer.api.service.vimeo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VimeoEditVideoSerivceTest {
    @InjectMocks
    private VimeoEditVideoService vimeoEditVideoService;

    @Test
    @DisplayName("Should edit video using video service")
    void edit() {
        String videoEndPoint = "/videos/707699077";
        String name = "Novo nome";
        String desc = "Nova descrição";

        vimeoEditVideoService.edit(videoEndPoint, name, desc);
    }
}