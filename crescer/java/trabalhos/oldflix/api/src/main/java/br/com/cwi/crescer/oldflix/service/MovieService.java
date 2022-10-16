package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.mapper.*;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.model.Situacao;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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


    public List<ListMovieResponse> list() {
        return movieRepository.findAll().stream()
                .map(movie->listMovieMapper.toResponse(movie))
                .collect(Collectors.toList());
    }

    public IncludeMovieIntoListResponse include(IncludeMovieIntoListRequest request) {
    Movie movie= IncludeMovieMapper.toEntity(request);
    movieRepository.save(movie);
    return includeMovieMapper.toResponse(movie);
    };

    public RentMovieResponse rent(Long id, RentMovieRequest request) {

    Movie movie = movieRepository.getById(id);

    movieValidator.isMovieAvailableRentValid(movie);

    movie.setRentResponsible(request.getRentResponsible());

    movie.setItAvailable(false);
    movie.setRentDate(LocalDate.now());
    movieRepository.save(movie);

    return rentMovieMapper.toResponse(movie);
    }

    public DevolutionMovieResponse devolution(Long id, DevolutionMovieRequest request) {
        Movie movie = movieRepository.getById(id);
        movieValidator.isMovieAvailableDevolutionValid(movie);
        movieValidator.isResponsibleTheSame(request,movie);
        movie.setRentResponsible("");
        movie.setItAvailable(true);
        movie.setRentDate(null);
        movieRepository.save(movie);
        return devolutionMovieMapper.toResponse(movie);
    }

    public MovieByIdResponse movieByid(Long id) {
        Movie movie = movieRepository.getById(id);
        MovieByIdResponse movieResponse=movieByIdMapper.toResponse(movie);

        if(!movieResponse.isItAvailable()){
            LocalDate DevolutionDateCalculated =  movieResponse.getRentDate().plusDays(movieResponse.getCategory().getDescricao());
            movieResponse.setDevolutionDate(DevolutionDateCalculated);
        }

        boolean thereIsDevolutionDate =  movieResponse.getDevolutionDate()!=null;
        if(thereIsDevolutionDate){
            if ( movieResponse.getDevolutionDate().isBefore(LocalDate.now())&& thereIsDevolutionDate){
            movieResponse.setSituation(Situacao.EM_ATRASO);
            }else if( movieResponse.getDevolutionDate().isAfter(LocalDate.now())&& thereIsDevolutionDate){
            movieResponse.setSituation(Situacao.EM_DIA);
             }
    }


        return movieResponse ;
    }

    public DeleteMovieResponse deleteById(Long id) {
        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.getById(id);
            movieRepository.deleteById(id);
            return deleteMovieMapper.toResponse(movie);
        }
        return null;
    }

    public ChangeMovieByIdResponse change(Long id, ChangeMovieByIdRequest request) {
        if(movieRepository.existsById(id)){
            Movie movie = movieRepository.getById(id);
            movie.setDesc(request.getDesc());
            movie.setTitle(request.getTitle());
            movie.setCategory(request.getCategory());
            movieRepository.save(movie);
            return changeMovieByIdMapper.toResponse(movie);
        }
        return null;
    }
}
