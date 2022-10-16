package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.MovieByIdResponse;
import br.com.cwi.crescer.oldflix.controller.response.RentMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.IncludeMovieMapper;
import br.com.cwi.crescer.oldflix.mapper.MovieByIdMapper;
import br.com.cwi.crescer.oldflix.mapper.RentMovieMapper;
import br.com.cwi.crescer.oldflix.model.Category;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.model.Situation;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
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
class MovieServiceTest {
    @InjectMocks
    private MovieService service;

    @Mock
    private MovieRepository repository;

    @Mock
    private MovieByIdMapper movieByIdMapper;

    @Mock
    private MovieFinderService movieFinderService;

    @Mock
    private DateService dateService;

    @Test
    @DisplayName("Should return if it is late, movieByidService")
    void shouldReturnIfItIsLate() {

        LocalDate date = LocalDate.parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDate = LocalDate.parse("08/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDateExpected = LocalDate.parse("06/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Long id = 2L;

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle("title");
        movie.setDescription("desc");
        movie.setCategory(Category.BRONZE);
        movie.setItAvailable(false);
        movie.setRentDate(date);
        movie.setRentResponsible("gustavo");

        MovieByIdResponse response = new MovieByIdResponse();
        response.setId(id);
        response.setTitle("title");
        response.setDescription("desc");
        response.setCategory(Category.BRONZE);
        response.setItAvailable(false);
        response.setRentDate(date);
        response.setRentResponsible("gustavo");


        Mockito.when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        Mockito.when(movieByIdMapper.toResponse(movie)).thenReturn(response);
        Mockito.when(dateService.now()).thenReturn(devolutionDate);

        MovieByIdResponse result = service.movieByid(id);

        Mockito.verify(movieFinderService).findByIdWithException(id);
        Mockito.verify(movieByIdMapper).toResponse(movie);
        Mockito.verify(dateService).now();

        Assertions.assertEquals(Situation.EM_ATRASO,result.getSituation());
        Assertions.assertEquals(result.getDevolutionDate(),devolutionDateExpected);

    }

    @Test
    @DisplayName("Should return if it is not late, movieByidService")
    void shouldReturnIfItIsNotLate() {

        LocalDate date = LocalDate.parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDate = LocalDate.parse("03/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate devolutionDateExpected = LocalDate.parse("06/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Long id = 2L;

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle("title");
        movie.setDescription("desc");
        movie.setCategory(Category.BRONZE);
        movie.setItAvailable(false);
        movie.setRentDate(date);
        movie.setRentResponsible("gustavo");

        MovieByIdResponse response = new MovieByIdResponse();
        response.setId(id);
        response.setTitle("title");
        response.setDescription("desc");
        response.setCategory(Category.BRONZE);
        response.setItAvailable(false);
        response.setRentDate(date);
        response.setRentResponsible("gustavo");


        Mockito.when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        Mockito.when(movieByIdMapper.toResponse(movie)).thenReturn(response);
        Mockito.when(dateService.now()).thenReturn(devolutionDate);

        MovieByIdResponse result = service.movieByid(id);

        Mockito.verify(movieFinderService).findByIdWithException(id);
        Mockito.verify(movieByIdMapper).toResponse(movie);
        Mockito.verify(dateService).now();

        Assertions.assertEquals(Situation.EM_DIA,result.getSituation());
        Assertions.assertEquals(result.getDevolutionDate(),devolutionDateExpected);
    }
    @Test
    @DisplayName("Should return if it is not available to give back, movieByidService")
    void shouldReturnNullIfItIsNotAvailableToGiveBack() {

        Long id = 2L;

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle("title");
        movie.setDescription("desc");
        movie.setCategory(Category.BRONZE);
        movie.setItAvailable(true);
        movie.setRentResponsible("gustavo");

        MovieByIdResponse response = new MovieByIdResponse();
        response.setId(id);
        response.setTitle("title");
        response.setDescription("desc");
        response.setCategory(Category.BRONZE);
        response.setItAvailable(true);
        response.setRentResponsible("gustavo");


        Mockito.when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        Mockito.when(movieByIdMapper.toResponse(movie)).thenReturn(response);


        MovieByIdResponse result = service.movieByid(id);

        Mockito.verify(movieFinderService).findByIdWithException(id);
        Mockito.verify(movieByIdMapper).toResponse(movie);


        Assertions.assertNull(result.getSituation());
        Assertions.assertEquals(result.getDevolutionDate(),null);
    }


    @Mock
    private IncludeMovieMapper includeMovieMapper;

    @Captor
    private ArgumentCaptor<Movie> movieCaptor;

    @Test
    @DisplayName("Should Include a movie, includeService")
    void shouldIncludeAMovie() {

        IncludeMovieIntoListRequest request = new IncludeMovieIntoListRequest();
        request.setTitle("title");
        request.setDescription("desc");
        request.setCategory(Category.OURO);

        Movie movie = new Movie();
        movie.setTitle("title");
        movie.setDescription("desc");
        movie.setCategory(Category.OURO);


        Mockito.when(includeMovieMapper.toEntity(request)).thenReturn(movie);


        service.include(request);


        Mockito.verify(includeMovieMapper).toEntity(request);
        Mockito.verify(includeMovieMapper).toResponse(movie);


        Mockito.verify(repository).save(movieCaptor.capture());
        Movie result = movieCaptor.getValue();

        Assertions.assertTrue(result.isItAvailable());
    }

    @Mock
    private MovieValidator movieValidator;
    @Mock
    private RentMovieMapper rentMovieMapper;

    @Test
    @DisplayName("Should Rent a movie, rentService")
    void shouldRentAMovie() {

        LocalDate date = LocalDate.parse("01/02/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        Long id = 2L;
        String responsible="gustavo";

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle("title");
        movie.setDescription("desc");
        movie.setCategory(Category.BRONZE);
        movie.setItAvailable(true);

        RentMovieRequest request = new RentMovieRequest();
        request.setRentResponsible(responsible);


        RentMovieResponse response = new RentMovieResponse();
        response.setId(id);
        response.setItAvailable(false);
        response.setRentDate(date);
        response.setRentResponsible(responsible);

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