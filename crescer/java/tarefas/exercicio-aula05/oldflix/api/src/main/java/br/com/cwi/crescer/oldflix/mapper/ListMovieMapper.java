package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.ListMovieResponse;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ListMovieMapper {
    public ListMovieResponse toResponse (Movie entity){
        return new ModelMapper().map(entity,ListMovieResponse.class);
    }
}
