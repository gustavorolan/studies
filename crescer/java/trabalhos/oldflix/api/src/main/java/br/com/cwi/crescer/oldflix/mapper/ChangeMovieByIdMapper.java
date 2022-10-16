package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.ChangeMovieByIdResponse;

import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ChangeMovieByIdMapper {
    public ChangeMovieByIdResponse toResponse(Movie entity){
        ChangeMovieByIdResponse response = new ModelMapper().map(entity,ChangeMovieByIdResponse.class);
        return response;
    }
}
