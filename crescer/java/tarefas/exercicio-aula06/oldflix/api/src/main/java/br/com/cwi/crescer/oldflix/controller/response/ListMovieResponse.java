package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListMovieResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isItAvailable;
    private Category category;
    private String imageUrl;
}
