package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.DevolutionMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.DevolutionMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevolutionMovieService  {

    @Autowired
    private DevolutionMovieMapper devolutionMovieMapper;
    @Autowired
    protected MovieValidator movieValidator;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    protected MovieFinderService movieFinderService;

    public DevolutionMovieResponse devolution(Long id, DevolutionMovieRequest request) {
        Movie movie = movieFinderService.findByIdWithException(id);
        movieValidator.isMovieAvailableDevolutionValid(movie);
        movieValidator.isResponsibleTheSame(request, movie);
        movie.setRentResponsible("");
        movie.setItAvailable(true);
        movie.setRentDate(null);
        movieRepository.save(movie);
        return devolutionMovieMapper.toResponse(movie);
    }
}
