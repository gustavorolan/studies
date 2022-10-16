package br.com.cwi.crescer.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IncludeUserAccountIntoPostRequest {
    @NotNull
    private Long idPost;
}
