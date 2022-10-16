package br.com.cwi.crescer.oldflix.validator;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieValidator {
    public static void isResponsibleTheSame(DevolutionMovieRequest request, Movie movie) {
        if(!request.getRentResponsible().equals(movie.getRentResponsible())){
            throw  new RuntimeException("não é o mesmo responsavel");
        }

    }

    public  void isMovieAvailableRentValid(Movie movie){
        if(!movie.isItAvailable()){
            throw new RuntimeException("Filme não está disponivel");

    }}
    public  void isMovieAvailableDevolutionValid(Movie movie){
        if(movie.isItAvailable()){
            throw new RuntimeException("Filme não está com você");

        }}
}
