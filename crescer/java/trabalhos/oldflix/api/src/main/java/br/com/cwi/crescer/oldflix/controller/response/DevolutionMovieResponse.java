package br.com.cwi.crescer.oldflix.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DevolutionMovieResponse {
    private Long id;
    private boolean isItAvailable;
    private String rentResponsible;
    private LocalDate rentDate;
}
