package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChangeMovieByIdRequest {

        private String title;
        private String desc;
        private Category category;

}
