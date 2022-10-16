package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.ListMovieResponse;
import br.com.cwi.crescer.oldflix.mapper.ListMovieMapper;
import br.com.cwi.crescer.oldflix.respository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListMovieService {

    @Autowired
    protected MovieRepository movieRepository;
    @Autowired
    private ListMovieMapper listMovieMapper;

    public List<ListMovieResponse> list() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> listMovieMapper.toResponse(movie))
                .collect(Collectors.toList());
    }
}
