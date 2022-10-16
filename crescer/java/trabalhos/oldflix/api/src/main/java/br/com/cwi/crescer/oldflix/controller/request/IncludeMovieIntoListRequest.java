package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.model.Category;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncludeMovieIntoListRequest {
    private String title;
    private String desc;
    @NotNull
    private Category category;
    private String imageUrl;
}
