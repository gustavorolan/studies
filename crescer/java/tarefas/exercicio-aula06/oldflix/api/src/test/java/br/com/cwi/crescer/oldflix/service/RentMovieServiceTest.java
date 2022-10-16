package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.RentMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.RentMovieMapper;
import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class RentMovieServiceTest {

    @InjectMocks
    private RentMovieService service;

    @Mock
    private MovieFinderService movieFinderService;

    @Mock
    private MovieRepository repository;

    @Mock
    private DateService dateService;

    @Mock
    private MovieValidator movieValidator;

    @Mock
    private RentMovieMapper rentMovieMapper;

    @Captor
    private ArgumentCaptor<Movie> movieCaptor;

    @Test
    @DisplayName("Should Rent a movie, rentService")
    void shouldRentAMovie() {

        LocalDate date = LocalDate.parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Long id = 2L;
        String responsible="gustavo";

        Movie movie = MovieFactory.getMovieBuilder();

        RentMovieRequest request = new RentMovieRequest();
        request.setRentResponsible(responsible);

        RentMovieResponse response = MovieFactory.getRentMovieResponseBuilder(id,date);

        Mockito.when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        Mockito.when(dateService.now()).thenReturn(date);
        Mockito.when(rentMovieMapper.toResponse(movie)).thenReturn(response);

        service.rent(id,request);

        Mockito.verify(dateService).now();
        Mockito.verify(movieFinderService).findByIdWithException(id);
        Mockito.verify(movieValidator).isMovieAvailableRentAndEditValid(movie);

        Mockito.verify(repository).save(movieCaptor.capture());
        Movie result = movieCaptor.getValue();

        Assertions.assertFalse(result.isItAvailable());

    }
}