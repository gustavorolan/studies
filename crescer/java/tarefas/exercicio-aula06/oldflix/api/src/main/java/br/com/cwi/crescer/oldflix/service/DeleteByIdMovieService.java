package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.DeleteMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.DeleteMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdMovieService  {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DeleteMovieMapper deleteMovieMapper;
    @Autowired
    protected MovieValidator movieValidator;
    @Autowired
    protected MovieFinderService movieFinderService;

    public DeleteMovieResponse deleteById(Long id) {
        movieValidator.isMovieIdValid(id);
        Movie movie = movieFinderService.findByIdWithException(id);
        movieRepository.deleteById(id);
        return deleteMovieMapper.toResponse(movie);
    }
}
