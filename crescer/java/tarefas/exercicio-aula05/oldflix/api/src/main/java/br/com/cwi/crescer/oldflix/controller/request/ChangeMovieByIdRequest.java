package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class ChangeMovieByIdRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String description;
        @NotEmpty
        private Category category;

}
