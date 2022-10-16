package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IncludeMovieIntoListResponse {
    private Long id;
    private String title;
    private Category category;
}
