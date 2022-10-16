package br.com.cwi.crescer.oldflix.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RentMovieRequest {
    @NotBlank
    private String rentResponsible;
}
