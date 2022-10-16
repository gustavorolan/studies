package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Situation;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieByIdResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isItAvailable;
    private Category category;
    private String rentResponsible;
    private LocalDate rentDate;
    private Situation situation;
    private LocalDate devolutionDate;
    private String imageUrl;
}
