package br.com.cwi.crescer.oldflix.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DevolutionMovieRequest {
    @NotBlank
    private String rentResponsible;
}
