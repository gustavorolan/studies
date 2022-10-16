package br.com.cwi.crescer.oldflix.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class RentMovieResponse {
    private Long id;
    private boolean isItAvailable;
    private String rentResponsible;
    private LocalDate rentDate;
}
