package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.DeleteMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.DeleteMovieMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import br.com.cwi.crescer.oldflix.service.useful.MovieFactory;
import br.com.cwi.crescer.oldflix.validator.MovieValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteByIdMovieServiceTest {
    @InjectMocks
    private DeleteByIdMovieService deleteByIdMovieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieValidator movieValidator;

    @Mock
    private MovieFinderService movieFinderService;

    @Mock
    private DeleteMovieMapper deleteMovieMapper;

    @Test
    void deleteById() {
        Long id =2L;
        response.setId(id);

        when(movieFinderService.findByIdWithException(id)).thenReturn(movie);
        when(deleteMovieMapper.toResponse(movie)).thenReturn(response);

        deleteByIdMovieService.deleteById(id);

        verify(movieValidator).isMovieIdValid(id);
        verify(movieFinderService).findByIdWithException(id);
        verify(deleteMovieMapper).toResponse(movie);
        verify(movieRepository).deleteById(id);

    }

    private DeleteMovieResponse response = new DeleteMovieResponse();
    private Movie movie = MovieFactory.getMovieBuilder();
}