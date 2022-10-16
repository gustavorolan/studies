package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.model.Video;
import br.com.cwi.crescer.api.repository.VideoRepository;
import br.com.cwi.crescer.api.service.finder.FindVideoWithThrow;
import br.com.cwi.crescer.api.util.VideoFactory;
import br.com.cwi.crescer.api.validator.IsUserLoggedValidator;
import br.com.cwi.crescer.api.validator.ParametersRegexValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@ExtendWith(MockitoExtension.class)
class SupportMaterialServiceTest {
    @InjectMocks
    private SupportMaterialService supportMaterialService;

    @Mock
    private FindVideoWithThrow findVideoWithThrow;

    @Mock
    private IsUserLoggedValidator isUserLoggedValidator;

    @Mock
    private VideoRepository repository;

    @Mock
    private ParametersRegexValidator parametersRegexValidator;

    @Captor
    private ArgumentCaptor<Video> captor;

    @Test
    @DisplayName("Should update an material to video")
    void uploadSupportMaterial() throws IOException {
        Long id = 1L;
        File file = new File("src/main/resources/teste.pdf");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test.pdf", inputStream);
        Video video = VideoFactory.get();

        Video videoExpected=VideoFactory.getBuilder().supportMaterial(bytes).build();
        ResponseMessage message = ResponseMessage
                .builder().response("You've added with success").build();

        Mockito.when(findVideoWithThrow.findByIdWithException(id)).thenReturn(video);

        ResponseMessage responseMessage = supportMaterialService.uploadSupportMaterial(mockMultipartFile, id);

        Mockito.verify(isUserLoggedValidator).verify(video.getAuthor());
        Mockito.verify(parametersRegexValidator).validatePdfContentType(mockMultipartFile.getContentType());
        Mockito.verify(parametersRegexValidator).validatePdfFileName(mockMultipartFile.getOriginalFilename());


        Mockito.verify(repository).save(captor.capture());

        Video value = captor.getValue();

        Assertions.assertEquals(videoExpected,value);
        Assertions.assertEquals(message,responseMessage);

    }
}