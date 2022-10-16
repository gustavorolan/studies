package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.response.IncludeMovieIntoListResponse;
import br.com.cwi.crescer.oldflix.mapper.IncludeMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeMovieService  {

    @Autowired
    private IncludeMovieMapper includeMovieMapper;
    @Autowired
    private MovieRepository movieRepository;

    public IncludeMovieIntoListResponse include(IncludeMovieIntoListRequest request) {
        Movie movie = includeMovieMapper.toEntity(request);
        movie.setRentDate(null);
        movie.setItAvailable(true);
        movie.setRentResponsible("");
        movieRepository.save(movie);
        return includeMovieMapper.toResponse(movie);
    }
}
