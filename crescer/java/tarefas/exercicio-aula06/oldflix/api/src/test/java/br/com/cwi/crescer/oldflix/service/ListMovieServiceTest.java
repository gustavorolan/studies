package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.ListMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.ListMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListMovieServiceTest {
    @InjectMocks
    private  ListMovieService listMovieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ListMovieMapper listMovieMapper;




    @Test
    @DisplayName("Should List All Movies")
    void shouldListAllMovies() {
        movieList.add(movie);

        when(movieRepository.findAll()).thenReturn(movieList);
        when(listMovieMapper.toResponse(movie)).thenReturn(response);

        List<ListMovieResponse> result = listMovieService.list();

        assertEquals(response, result.get(0));

    }
    private List<Movie> movieList = new ArrayList();

    private Movie movie = MovieFactory.getMovieBuilder();

    private ListMovieResponse response = MovieFactory.getListMovieResponseBuilder();
}