package br.com.cwi.crescer.api.service;

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
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
class GetMaterialFromVideoServiceTest {

    @InjectMocks
    private GetMaterialFromVideoService getMaterialFromVideoService;


    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Test
    @DisplayName("Should return a pdf")
    void download() throws IOException {
        Long id = 1L;
        File file = new File("src/main/resources/teste.pdf");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        Video video = VideoFactory.getBuilder()
                .supportMaterial(bytes)
                .build();

        Mockito.when(findVideoWithThrow.findByIdAndActiveWithException(id,true)).thenReturn(video);

        ResponseEntity<Resource> result = getMaterialFromVideoService.download(id);

        Mockito.verify(findVideoWithThrow).findByIdAndActiveWithException(id,true);

        Assertions.assertTrue(result.getBody().isReadable());
        Assertions.assertTrue(result.getBody().exists());
    }
}