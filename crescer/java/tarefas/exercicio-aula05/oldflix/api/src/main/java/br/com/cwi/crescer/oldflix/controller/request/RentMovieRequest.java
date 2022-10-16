package br.com.cwi.crescer.oldflix.controller.request;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RentMovieRequest {
    @NotEmpty
    private String rentResponsible;
}
