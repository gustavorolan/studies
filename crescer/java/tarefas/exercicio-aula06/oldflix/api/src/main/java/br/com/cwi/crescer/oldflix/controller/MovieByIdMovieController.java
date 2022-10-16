package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.response.MovieByIdResponse;
import br.com.cwi.crescer.oldflix.service.IncludeMovieService;
import br.com.cwi.crescer.oldflix.service.MovieByIdMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieByIdMovieController {
    @Autowired
    private MovieByIdMovieService movieByIdMovieService;

    @GetMapping("/{id}")
    public MovieByIdResponse movieById(@PathVariable Long id) {
        return movieByIdMovieService.movieByid(id);
    }
}
