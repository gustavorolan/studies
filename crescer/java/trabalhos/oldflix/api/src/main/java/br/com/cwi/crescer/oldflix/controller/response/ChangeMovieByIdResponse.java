package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeMovieByIdResponse {
    private Long id;
    private String title;
    private String desc;
    private Category category;
}
