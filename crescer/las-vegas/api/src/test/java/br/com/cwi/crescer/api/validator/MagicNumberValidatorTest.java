package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.util.AttachmentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class MagicNumberValidatorTest {

    @InjectMocks
    private MagicNumberValidator magicNumberValidator;

    @Test
    @DisplayName("Should validate file type for being png or jpeg")
    void validateAttachment() throws IOException {
        File file = new File("src/main/resources/teste.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video.mp4", fileInputStream);

        magicNumberValidator.validateAttachment(mockMultipartFile);
    }

    @Test
    @DisplayName("Should invalidate file type for not being png or jpeg")
    void invalidateAttachment() throws IOException {
        File file = new File("src/main/resources/teste.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("video.mp4", fileInputStream);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            magicNumberValidator.validateAttachment(mockMultipartFile);
        });

        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Image type is not acceptable\"", exception.getMessage());
    }

    @Test
    @DisplayName("Verifies if it's a picture, it uses initial bytes of png and jpeg")
    void validateAttachmentWithError() throws IOException {
        MockMultipartFile bytes = AttachmentFactory.mockTextFile();

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            magicNumberValidator.validateAttachment(bytes);
        });
        Assertions.assertEquals("406 NOT_ACCEPTABLE \"Error\"", exception.getMessage());
    }
}