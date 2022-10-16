package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.DeleteMovieResponse;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class DeleteMovieMapper {
    public DeleteMovieResponse toResponse(Movie entity){
        DeleteMovieResponse response = new ModelMapper().map(entity,DeleteMovieResponse.class);
        return response;
    }
}
