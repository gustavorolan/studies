package br.com.cwi.crescer.api.service.cloud;

import br.com.cwi.crescer.api.model.SafeSearchAnnotation;
import br.com.cwi.crescer.api.model.SafeSearchObject;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;


@Service
public class SafeSearchConverterService {
    public SafeSearchAnnotation converter (String json){
        Gson gson = new Gson();
        SafeSearchObject safeSearchAnnotation = gson.fromJson(json, SafeSearchObject.class);
        return safeSearchAnnotation.getResponses().get(0).getSafeSearchAnnotation();
    }
}
