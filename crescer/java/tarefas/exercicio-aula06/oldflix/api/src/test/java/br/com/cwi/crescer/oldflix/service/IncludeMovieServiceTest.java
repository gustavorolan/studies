package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.mapper.IncludeMovieMapper;
import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IncludeMovieServiceTest {

    @InjectMocks
    private IncludeMovieService service;

    @Mock
    private MovieRepository repository;

    @Mock
    private IncludeMovieMapper includeMovieMapper;

    @Captor
    private ArgumentCaptor<Movie> movieCaptor;



    @Test
    @DisplayName("Should Include a movie, includeService")
    void shouldIncludeAMovie() {

        Mockito.when(includeMovieMapper.toEntity(request)).thenReturn(movie);


        service.include(request);


        Mockito.verify(includeMovieMapper).toEntity(request);
        Mockito.verify(includeMovieMapper).toResponse(movie);


        Mockito.verify(repository).save(movieCaptor.capture());
        Movie result = movieCaptor.getValue();

        Assertions.assertTrue(result.isItAvailable());
    }

    private IncludeMovieIntoListRequest request = MovieFactory.getIncludeMovieResponseBuilder();

    private Movie movie =  MovieFactory.getMovieBuilder();
}