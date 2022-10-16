package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MovieFinderServiceTest {
    @InjectMocks
    private MovieFinderService movieFinderService;

    @Mock
    private MovieRepository movieRepository;


    @Test
    @DisplayName("Should throw an exception")
     void shouldThrowAnException()  {
        Long id = 2L;
        Optional empty = Optional.empty();

        Mockito.when(movieRepository.findById(id)).thenReturn(empty);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            movieFinderService.findByIdWithException(id);
        });

        Mockito.verify(movieRepository).findById(id);

        Assertions.assertEquals("404 NOT_FOUND \"não existe um filme com o id informado\"", exception.getMessage());

    }
    @Test
    @DisplayName("Should do nothing")
    void shouldDoNothing()  {
        Long id = 2L;

        Movie movie = new Movie();

        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        movieFinderService.findByIdWithException(id);

        Mockito.verify(movieRepository).findById(id);
    }
}