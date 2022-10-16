package br.com.cwi.crescer.oldflix.service.useful;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Movie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieFactory {

    private static Long id = 2L;
    private String responsible="gustavo";

    public static Movie getMovieBuilder(){
        return Movie.builder()
                .id(id)
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .isItAvailable(true).build();
    }

    public static ListMovieResponse getListMovieResponseBuilder(){
        return ListMovieResponse.builder()
                .id(id)
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .isItAvailable(true)
                .build();
    }


    public static IncludeMovieIntoListRequest getIncludeMovieResponseBuilder(){
        return IncludeMovieIntoListRequest.builder()
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .build();
    }
    public static RentMovieResponse getRentMovieResponseBuilder(Long id, LocalDate date){
        return RentMovieResponse.builder()
                .id(id)
                .isItAvailable(false)
                .rentDate(date)
                .rentResponsible("responsible")
                .build();
    }

    public static ChangeMovieByIdRequest getChangeMovieByIdRequestBuilder(){
        return ChangeMovieByIdRequest.builder()
                .description("desc")
                .title("title")
                .category(Category.BRONZE)
                .build();
    }

    public static ChangeMovieByIdResponse getChangeMovieByIdResponseBuilder(){
        return ChangeMovieByIdResponse.builder()
                .id(id)
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .build();

    }

    public static DevolutionMovieResponse getDevolutionMovieResponseBuilder(LocalDate date){
        return DevolutionMovieResponse.builder()
                .id(id)
                .isItAvailable(true)
                .rentResponsible("responsible")
                .rentDate(date)
                .build();
    }

    private static LocalDate date = LocalDate
            .parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    public static Movie getMovieByIdFactory(Long id){
        return Movie.builder()
                .id(id)
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .isItAvailable(false)
                .rentDate(date)
                .rentResponsible("responsible")
                .build();
    }
    public static MovieByIdResponse getMovieResponseByIdFactory(Long id){
        return MovieByIdResponse.builder()
                .id(id)
                .title("title")
                .description("desc")
                .category(Category.BRONZE)
                .isItAvailable(false)
                .rentDate(date)
                .rentResponsible("responsible")
                .build();
    }

    public static DevolutionMovieRequest getDevolutionMovieRequestFactory(){
        return DevolutionMovieRequest.builder()
                .rentResponsible("responsible")
                .build();
    }


}
