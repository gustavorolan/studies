package br.com.cwi.crescer.oldflix.validator;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieValidatorTest {
    @InjectMocks
    private MovieValidator movieValidator;

    @Mock
    private MovieRepository movieRepository;


    @Test
    @DisplayName("if Responsible Is not, Then, It Throws an Exception")
    void ifResponsibleIsNotTheSameThrowsException() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            movieValidator.isResponsibleTheSame(request,movie);
        });

        Assertions.assertEquals("não é o mesmo responsavel", exception.getMessage());

    }

    @Test
    @DisplayName("should throw an exception if movie is not available to rent or edit")
    void shouldThrowAnExceptonIfIsNotMovieAvailableRentAndEditValid() {
        movie.setItAvailable(false);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            movieValidator.isMovieAvailableRentAndEditValid(movie);
        });

        Assertions.assertEquals("Filme não está disponivel", exception.getMessage());
    }

    @Test
    @DisplayName("should throw an exception if movie is not available to give back")
    void shouldThrowAnExceptionIfIsNotMovieAvailableDevolutionValid() {

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            movieValidator.isMovieAvailableDevolutionValid(movie);
        });

        Assertions.assertEquals("Filme não está com você", exception.getMessage());

    }

    @Test
    @DisplayName("should throw an exception if movie id is not valid")
    void shouldThrowAnExceptionIfIsNotMovieIdValid() {
        Long id = 2l;

        when(movieRepository.existsById(id)).thenReturn(false);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            movieValidator.isMovieIdValid(id);
        });

        Assertions.assertEquals("Não é um id valido", exception.getMessage());
    }

    private Movie movie = MovieFactory.getMovieBuilder();
    private DevolutionMovieRequest request = MovieFactory.getDevolutionMovieRequestFactory();

}