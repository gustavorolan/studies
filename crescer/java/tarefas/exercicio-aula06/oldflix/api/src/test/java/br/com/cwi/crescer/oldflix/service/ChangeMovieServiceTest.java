package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.response.ChangeMovieByIdResponse;
import br.com.cwi.crescer.oldflix.mapper.ChangeMovieByIdMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ChangeMovieServiceTest {
    @InjectMocks
    private ChangeMovieService service;

    @Mock
    private MovieRepository repository;

    @Captor
    private ArgumentCaptor<Movie> movieCaptor;

    @Mock
    private  MovieFinderService movieFinderService;

    @Mock
    private MovieValidator movieValidator;

    @Mock
    private ChangeMovieByIdMapper changeMovieByIdMapper;

    @Test
    @DisplayName("should set request values to movie, changeService")
    void shouldSetRequestValuesToMovie() {

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(changeMovieByIdMapper.toResponse(movie)).thenReturn(response);

        service.change(id,request);

        Mockito.verify(movieValidator).isMovieIdValid(id);
        Mockito.verify(movieValidator).isMovieAvailableRentAndEditValid(movie);
        Mockito.verify(repository).save(movieCaptor.capture());
        Mockito.verify(movieFinderService).findByIdWithException(id);

        Movie result = movieCaptor.getValue();

        assertEquals(request.getCategory(),result.getCategory());
        assertEquals(request.getTitle(),result.getTitle());
        assertEquals(request.getDescription(),result.getDescription());
    }



    private  Movie movie = MovieFactory.getMovieBuilder();

    private  Long id = 2L;

    private ChangeMovieByIdRequest request = MovieFactory.getChangeMovieByIdRequestBuilder();

    private ChangeMovieByIdResponse response = MovieFactory.getChangeMovieByIdResponseBuilder();

}