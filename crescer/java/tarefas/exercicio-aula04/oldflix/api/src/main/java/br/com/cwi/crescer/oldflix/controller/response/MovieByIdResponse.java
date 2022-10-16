package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class MovieByIdResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isItAvailable;
    private Category category;
    private String rentResponsible;
    private LocalDate rentDate;
    private Situacao situation;
    private LocalDate devolutionDate;
    private String imageUrl;
}
