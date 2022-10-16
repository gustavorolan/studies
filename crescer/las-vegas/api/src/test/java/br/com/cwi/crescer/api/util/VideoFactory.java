package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.VideoRequest;
import br.com.cwi.crescer.api.controller.response.VideoResponse;
import br.com.cwi.crescer.api.model.Video;

public class VideoFactory {
    public static Video.VideoBuilder getBuilder(){
        return Video.builder()
                .id(1L)
                .name("name")
                .description("desc")
                .endPoint("endPoint")
                .link("link")
                .score(5);
    }
    public static Video get(){
        return getBuilder().build();
    }

    public static VideoResponse.VideoResponseBuilder getVideoBuilderResponse(){
       return VideoResponse.builder()
               .id(1L)
               .name("name")
               .description("desc")
               .link("link")
               .score(5);
    }

    public static VideoResponse getVideoResponse(){
        return getVideoBuilderResponse().build();
    }

    public static VideoRequest getVideoRequest(){
        return VideoRequest.builder()
                .name("name")
                .link("link")
                .description("desc")
                .build();
    }

}
