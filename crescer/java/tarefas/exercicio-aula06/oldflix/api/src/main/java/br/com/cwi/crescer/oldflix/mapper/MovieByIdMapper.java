package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.response.MovieByIdResponse;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class MovieByIdMapper {
    public MovieByIdResponse toResponse (Movie entity){
        MovieByIdResponse response =new ModelMapper().map(entity,MovieByIdResponse.class);
        return response ;
    }
}
