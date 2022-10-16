package br.com.cwi.crescer.api.controller.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private List<ErrorDetailResponse> errors;

    @Builder
    @Getter
    public static class ErrorDetailResponse {

        private String field;
        private String message;
    }
}

