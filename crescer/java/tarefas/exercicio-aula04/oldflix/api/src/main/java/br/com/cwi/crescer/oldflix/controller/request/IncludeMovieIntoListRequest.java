package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.model.Category;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class IncludeMovieIntoListRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Category category;
    @NotBlank
    private String imageUrl;
}
