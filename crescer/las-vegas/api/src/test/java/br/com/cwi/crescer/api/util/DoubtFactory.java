package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.model.Doubt;

public class DoubtFactory {
    public static DoubtResponse.DoubtResponseBuilder getDoubtBuilderResponse(){
        return DoubtResponse.builder()
                .id(1L)
                .description("desc")
                .finished(true)
                .active(true);
    }
    public static DoubtResponse getDoubtResponse(){
        return getDoubtBuilderResponse().build();
    }

    public static Doubt.DoubtBuilder getDoubtBuilder(){
        return Doubt.builder()
                .id(1L)
                .description("desc")
                .finished(true)
                .active(true);
    }

    public static Doubt getDoubt(){
        return getDoubtBuilder().build();
    }

    public static DoubtRequest getDoubtRequest(){
        return DoubtRequest.builder()
                .description("desc")
                .build();
    }
}
