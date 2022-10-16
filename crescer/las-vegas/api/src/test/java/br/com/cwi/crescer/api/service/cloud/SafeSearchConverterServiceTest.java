package br.com.cwi.crescer.api.service.cloud;

import br.com.cwi.crescer.api.model.SafeSearchAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SafeSearchConverterServiceTest {
    @InjectMocks
    private SafeSearchConverterService safeSearchConverterService;

    @Test
    void converter() {
        String responseFromServer="{\n" +
                "    \"responses\": [\n" +
                "        {\n" +
                "            \"safeSearchAnnotation\": {\n" +
                "                \"adult\": \"UNLIKELY\",\n" +
                "                \"spoof\": \"POSSIBLE\",\n" +
                "                \"medical\": \"VERY_UNLIKELY\",\n" +
                "                \"violence\": \"UNLIKELY\",\n" +
                "                \"racy\": \"POSSIBLE\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        SafeSearchAnnotation safeSearchAnnotation = SafeSearchAnnotation.builder()
                .adult("UNLIKELY")
                .spoof("POSSIBLE")
                .medical("VERY_UNLIKELY")
                .violence("UNLIKELY")
                .racy("POSSIBLE")
                .build();

        SafeSearchAnnotation result = safeSearchConverterService.converter(responseFromServer);


        Assertions.assertEquals(safeSearchAnnotation.getAdult(),result.getAdult());
        Assertions.assertEquals(safeSearchAnnotation.getMedical(),result.getMedical());
        Assertions.assertEquals(safeSearchAnnotation.getRacy(),result.getRacy());
        Assertions.assertEquals(safeSearchAnnotation.getSpoof(),result.getSpoof());
        Assertions.assertEquals(safeSearchAnnotation.getViolence(),result.getViolence());
    }
}