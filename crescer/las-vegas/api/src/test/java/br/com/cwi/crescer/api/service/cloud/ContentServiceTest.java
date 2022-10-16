package br.com.cwi.crescer.api.service.cloud;

import br.com.cwi.crescer.api.model.SafeSearchAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @InjectMocks
    private ContentService contentService;


    @Test
    @DisplayName("Should throw an error if image has adult content")
    void verifierLikelyAdult(){
        String response = "400 BAD_REQUEST \"\n" +
                "Image With Adult Content\n" +
                "Image With Medical Content\n" +
                "Image With Racy Content\n" +
                "Image With Spoof Content\n" +
                "Image With Violence Content\"";

        SafeSearchAnnotation likely = SafeSearchAnnotation
                .builder()
                .adult("LIKELY")
                .medical("POSSIBLE")
                .racy("POSSIBLE")
                .spoof("POSSIBLE")
                .violence("POSSIBLE")
                .build();
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            contentService.verifyContent(likely);
        });

        Assertions.assertEquals(response, exception.getMessage());
    }

}