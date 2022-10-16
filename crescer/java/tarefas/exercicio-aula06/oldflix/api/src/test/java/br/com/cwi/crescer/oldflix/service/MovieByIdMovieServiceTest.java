package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.MovieByIdResponse;
import br.com.cwi.crescer.oldflix.mapper.MovieByIdMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.model.Situation;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieByIdMovieServiceTest {

    @InjectMocks
    private MovieByIdMovieService service;


    @Mock
    private MovieByIdMapper movieByIdMapper;

    @Mock
    private MovieFinderService movieFinderService;

    @Mock
    private DateService dateService;


    @Test
    @DisplayName("Should return if it is late, movieByidService")
    void shouldReturnIfItIsLate() {
        LocalDate devolutionDate = LocalDate
                .parse("08/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDateExpected = LocalDate
                .parse("06/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(movieByIdMapper.toResponse(movie)).thenReturn(response);
        when(dateService.now()).thenReturn(devolutionDate);

        MovieByIdResponse result = service.movieByid(id);

        verify(movieFinderService).findByIdWithException(id);
        verify(movieByIdMapper).toResponse(movie);
        verify(dateService).now();

        assertEquals(Situation.EM_ATRASO,result.getSituation());
        assertEquals(devolutionDateExpected,result.getDevolutionDate());

    }

    @Test
    @DisplayName("Should return if it is not late, movieByidService")
    void shouldReturnIfItIsNotLate() {

        LocalDate devolutionDate = LocalDate
                .parse("03/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDateExpected = LocalDate
                .parse("06/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(movieByIdMapper.toResponse(movie)).thenReturn(response);
        when(dateService.now()).thenReturn(devolutionDate);

        MovieByIdResponse result = service.movieByid(id);

        verify(movieFinderService).findByIdWithException(id);
        verify(movieByIdMapper).toResponse(movie);
        verify(dateService).now();

        assertEquals(Situation.EM_DIA,result.getSituation());
        assertEquals(devolutionDateExpected,result.getDevolutionDate());
    }
    @Test
    @DisplayName("Should return if it is not available to give back, movieByidService")
    void shouldReturnNullIfItIsNotAvailableToGiveBack() {
        movie.setItAvailable(true);
        response.setItAvailable(true);

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(movieByIdMapper.toResponse(movie)).thenReturn(response);

        MovieByIdResponse result = service.movieByid(id);

        verify(movieFinderService).findByIdWithException(id);
        verify(movieByIdMapper).toResponse(movie);

        assertNull(result.getSituation());
        assertEquals(null,result.getDevolutionDate());
    }
    private  Long id = 2L;

    private Movie movie = MovieFactory.getMovieByIdFactory(id);

    private MovieByIdResponse response = MovieFactory.getMovieResponseByIdFactory(id);
}