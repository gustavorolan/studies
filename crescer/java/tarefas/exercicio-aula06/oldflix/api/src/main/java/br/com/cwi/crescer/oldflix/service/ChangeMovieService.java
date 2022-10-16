package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.response.ChangeMovieByIdResponse;
import br.com.cwi.crescer.oldflix.mapper.ChangeMovieByIdMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeMovieService  {

    @Autowired
    private ChangeMovieByIdMapper changeMovieByIdMapper;
    @Autowired
    private  MovieFinderService movieFinderService;
    @Autowired
    private MovieValidator movieValidator;
    @Autowired
    private MovieRepository movieRepository;

    public ChangeMovieByIdResponse change(Long id, ChangeMovieByIdRequest request) {
        movieValidator.isMovieIdValid(id);
        Movie movie = movieFinderService.findByIdWithException(id);
        movieValidator.isMovieAvailableRentAndEditValid(movie);
        movie.setDescription(request.getDescription());
        movie.setTitle(request.getTitle());
        movie.setCategory(request.getCategory());
        movieRepository.save(movie);
        return changeMovieByIdMapper.toResponse(movie);

    }
}
