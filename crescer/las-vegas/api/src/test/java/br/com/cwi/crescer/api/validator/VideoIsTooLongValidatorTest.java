package br.com.cwi.crescer.api.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class VideoIsTooLongValidatorTest {

    @InjectMocks
    private VideoIsTooLongValidator videoIsTooLongValidator;

    @Test
    @DisplayName("Should throw an error if video is too long")
    void verify() throws IOException {
        File file = new File("src/main/resources/testFile.mp4");
        FileInputStream fileInputStream = new FileInputStream(file);
        int videoTime=5;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            videoIsTooLongValidator.verify(fileInputStream,videoTime);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Video is way too long | \"", exception.getMessage());

    }
}