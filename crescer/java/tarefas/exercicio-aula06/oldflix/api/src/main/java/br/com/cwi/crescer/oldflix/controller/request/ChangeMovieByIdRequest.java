package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.model.Category;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChangeMovieByIdRequest {
        @NotEmpty
        private String title;
        @NotEmpty
        private String description;
        @NotEmpty
        private Category category;

}
