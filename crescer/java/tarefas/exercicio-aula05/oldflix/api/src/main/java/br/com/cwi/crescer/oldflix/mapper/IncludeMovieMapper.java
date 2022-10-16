package br.com.cwi.crescer.oldflix.mapper;

import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.response.IncludeMovieIntoListResponse;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class IncludeMovieMapper {
    public  Movie toEntity(IncludeMovieIntoListRequest request){
        Movie movie = new ModelMapper().map(request,Movie.class);
        return movie;
    };
    public IncludeMovieIntoListResponse toResponse (Movie entity){
        return new ModelMapper().map(entity,IncludeMovieIntoListResponse.class);
    }
}
