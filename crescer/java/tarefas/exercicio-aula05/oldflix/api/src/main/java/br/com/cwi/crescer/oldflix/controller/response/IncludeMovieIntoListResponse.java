package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class IncludeMovieIntoListResponse {
    private Long id;
    private String title;
    private Category category;
}
