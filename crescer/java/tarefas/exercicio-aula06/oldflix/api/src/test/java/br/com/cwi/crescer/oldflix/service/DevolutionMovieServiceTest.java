package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.DevolutionMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.DevolutionMovieMapper;
import br.com.cwi.crescer.oldflix.mapper.RentMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DevolutionMovieServiceTest {
    @InjectMocks
    private DevolutionMovieService service;

    @Mock
    private MovieFinderService movieFinderService;

    @Mock
    private MovieRepository repository;

    @Mock
    private DateService dateService;

    @Mock
    private MovieValidator movieValidator;

    @Mock
    private DevolutionMovieMapper devolutioMovieMapper;

    @Captor
    private ArgumentCaptor<Movie> movieCaptor;


    @Test
    @DisplayName("should set values when is given back, devolutionService")
    void devolution() {
        movie.setRentResponsible("responsible");
        movie.setRentDate(date);
        movie.setItAvailable(false);

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(devolutioMovieMapper.toResponse(movie)).thenReturn(response);


        service.devolution(id,request);

        verify(movieFinderService).findByIdWithException(id);
        verify(movieValidator).isMovieAvailableDevolutionValid(movie);
        verify(movieValidator).isResponsibleTheSame(request,movie);

        Mockito.verify(repository).save(movieCaptor.capture());
        Movie result = movieCaptor.getValue();

        assertEquals("",result.getRentResponsible());
        assertEquals(true,result.isItAvailable());
        assertEquals(null,result.getRentDate());
    }


    private Long id = 2L;

    LocalDate date = LocalDate.parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    private Movie movie = MovieFactory.getMovieBuilder();

    private DevolutionMovieRequest request = MovieFactory.getDevolutionMovieRequestFactory();


    private DevolutionMovieResponse response = MovieFactory.getDevolutionMovieResponseBuilder(date);

}