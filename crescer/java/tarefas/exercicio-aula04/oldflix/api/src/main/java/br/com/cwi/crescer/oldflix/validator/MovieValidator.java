package br.com.cwi.crescer.oldflix.validator;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieValidator {
    @Autowired
    private MovieRepository movieRepository;

    public static void isResponsibleTheSame(DevolutionMovieRequest request, Movie movie) {
        if(!request.getRentResponsible().equals(movie.getRentResponsible())){
            throw  new RuntimeException("não é o mesmo responsavel");
        }

    }

    public  void isMovieAvailableRentAndEditValid(Movie movie){
        if(!movie.isItAvailable()){
            throw new RuntimeException("Filme não está disponivel");

    }}
    public  void isMovieAvailableDevolutionValid(Movie movie){
        if(movie.isItAvailable()){
            throw new RuntimeException("Filme não está com você");

        }}
    public void isMovieIdValid(Long id){
        if(!movieRepository.existsById(id)){
            throw new RuntimeException("Não é um id valido");
        }
    }
}
