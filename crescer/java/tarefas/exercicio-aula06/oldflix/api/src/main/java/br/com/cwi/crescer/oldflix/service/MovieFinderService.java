package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MovieFinderService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie findByIdWithException(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "não existe um filme com o id informado"));
    }

}
