package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.RentMovieResponse;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component

public class RentMovieMapper {
    public RentMovieResponse toResponse(Movie entity){
        RentMovieResponse response = new ModelMapper().map(entity,RentMovieResponse.class);
        return response;
    }
}
