package br.com.cwi.crescer.api.service.cloud;

import br.com.cwi.crescer.api.model.SafeSearchAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContentService {
    private static final String POSSIBLE = "POSSIBLE";
    private static final String LIKELY = "LIKELY";

    public void verifyContent(SafeSearchAnnotation safeSearchAnnotation) {
        String string = "";
        string = string + verifier(safeSearchAnnotation.getAdult(), "Image With Adult Content");
        string = string + verifier(safeSearchAnnotation.getMedical(), "Image With Medical Content");
        string = string + verifier(safeSearchAnnotation.getRacy(), "Image With Racy Content");
        string = string + verifier(safeSearchAnnotation.getSpoof(), "Image With Spoof Content");
        string = string + verifier(safeSearchAnnotation.getViolence(), "Image With Violence Content");

        if (!string.isBlank()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, string);
        }
    }
    private String verifier(String annotation, String problem){
        if(annotation.equals(POSSIBLE)||annotation.equals(LIKELY)) {
            return "\n" + problem  ;
        }
        return "";
    }
}
