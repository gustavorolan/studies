package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.mapper.*;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.model.Situation;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ListMovieMapper listMovieMapper;
    @Autowired
    private IncludeMovieMapper includeMovieMapper;
    @Autowired
    private RentMovieMapper rentMovieMapper;
    @Autowired
    private DevolutionMovieMapper devolutionMovieMapper;
    @Autowired
    private MovieByIdMapper movieByIdMapper;
    @Autowired
    private DeleteMovieMapper deleteMovieMapper;
    @Autowired
    private ChangeMovieByIdMapper changeMovieByIdMapper;
    @Autowired
    private MovieValidator movieValidator;
    @Autowired
    private MovieFinderService movieFinderService;
    @Autowired
    private DateService dateService;


    public List<ListMovieResponse> list() {
        return movieRepository.findAll().stream()
                .map(movie->listMovieMapper.toResponse(movie))
                .collect(Collectors.toList());
    }

    public IncludeMovieIntoListResponse include(IncludeMovieIntoListRequest request) {
    Movie movie= includeMovieMapper.toEntity(request);
    movie.setRentDate(null);
    movie.setItAvailable(true);
    movie.setRentResponsible("");
    movieRepository.save(movie);
    return includeMovieMapper.toResponse(movie);
    };

    public RentMovieResponse rent(Long id, RentMovieRequest request) {

    Movie movie = movieFinderService.findByIdWithException(id);

    movieValidator.isMovieAvailableRentAndEditValid(movie);

    movie.setRentResponsible(request.getRentResponsible());
    movie.setItAvailable(false);
    movie.setRentDate(dateService.now());

    movieRepository.save(movie);

    return rentMovieMapper.toResponse(movie);
    }

    public DevolutionMovieResponse devolution(Long id, DevolutionMovieRequest request) {
        Movie movie = movieFinderService.findByIdWithException(id);
        movieValidator.isMovieAvailableDevolutionValid(movie);
        movieValidator.isResponsibleTheSame(request,movie);
        movie.setRentResponsible("");
        movie.setItAvailable(true);
        movie.setRentDate(null);
        movieRepository.save(movie);
        return devolutionMovieMapper.toResponse(movie);
    }

    public MovieByIdResponse movieByid(Long id) {
        Movie movie = movieFinderService.findByIdWithException(id);

        MovieByIdResponse movieResponse=movieByIdMapper.toResponse(movie);

        if(!movieResponse.isItAvailable()){
            LocalDate DevolutionDateCalculated =  movieResponse.getRentDate().plusDays(movieResponse.getCategory().getDescricao());
            movieResponse.setDevolutionDate(DevolutionDateCalculated);
            if ( movieResponse.getDevolutionDate().isBefore(dateService.now())){
                movieResponse.setSituation(Situation.EM_ATRASO);
            }else{
                movieResponse.setSituation(Situation.EM_DIA);
            }
        }

        return movieResponse ;
    }

    public DeleteMovieResponse deleteById(Long id) {
        movieValidator.isMovieIdValid(id);
        Movie movie = movieFinderService.findByIdWithException(id);
        movieRepository.deleteById(id);
        return deleteMovieMapper.toResponse(movie);
    }

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
