package br.com.cwi.crescer.oldflix.controller.response;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DevolutionMovieResponse {
    private Long id;
    private boolean isItAvailable;
    private String rentResponsible;
    private LocalDate rentDate;
}
