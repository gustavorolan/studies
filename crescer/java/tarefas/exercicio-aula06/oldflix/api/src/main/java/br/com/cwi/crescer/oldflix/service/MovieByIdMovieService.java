package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.controller.response.MovieByIdResponse;
import br.com.cwi.crescer.oldflix.mapper.MovieByIdMapper;
import br.com.cwi.crescer.oldflix.model.Movie;
import br.com.cwi.crescer.oldflix.model.Situation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovieByIdMovieService  {

    @Autowired
    private MovieByIdMapper movieByIdMapper;
    @Autowired
    protected MovieFinderService movieFinderService;
    @Autowired
    protected DateService dateService;

    public MovieByIdResponse movieByid(Long id) {
        Movie movie = movieFinderService.findByIdWithException(id);

        MovieByIdResponse movieResponse = movieByIdMapper.toResponse(movie);

        if (!movieResponse.isItAvailable()) {
            LocalDate DevolutionDateCalculated = movieResponse.getRentDate().plusDays(movieResponse.getCategory().getDescricao());
            movieResponse.setDevolutionDate(DevolutionDateCalculated);
            if (movieResponse.getDevolutionDate().isBefore(dateService.now())) {
                movieResponse.setSituation(Situation.EM_ATRASO);
            } else {
                movieResponse.setSituation(Situation.EM_DIA);
            }
        }

        return movieResponse;
    }
}
