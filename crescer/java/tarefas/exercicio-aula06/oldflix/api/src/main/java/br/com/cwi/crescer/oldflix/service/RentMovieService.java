package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.RentMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.RentMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentMovieService  {

    @Autowired
    protected MovieValidator movieValidator;
    @Autowired
    protected MovieFinderService movieFinderService;
    @Autowired
    protected DateService dateService;
    @Autowired
    private RentMovieMapper rentMovieMapper;
    @Autowired
    private MovieRepository movieRepository;

    public RentMovieResponse rent(Long id, RentMovieRequest request) {

        Movie movie = movieFinderService.findByIdWithException(id);

        movieValidator.isMovieAvailableRentAndEditValid(movie);

        movie.setRentResponsible(request.getRentResponsible());
        movie.setItAvailable(false);
        movie.setRentDate(dateService.now());

        movieRepository.save(movie);

        return rentMovieMapper.toResponse(movie);
    }
}
