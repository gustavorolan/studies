package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.DevolutionMovieResponse;

import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DevolutionMovieMapper {
    public DevolutionMovieResponse toResponse(Movie entity){
        DevolutionMovieResponse response = new ModelMapper().map(entity,DevolutionMovieResponse.class);
        return response;
    }
}
