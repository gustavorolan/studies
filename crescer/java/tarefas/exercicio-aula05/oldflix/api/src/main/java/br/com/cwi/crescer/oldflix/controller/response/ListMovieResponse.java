package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListMovieResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isItAvailable;
    private Category category;
    private String imageUrl;
}
