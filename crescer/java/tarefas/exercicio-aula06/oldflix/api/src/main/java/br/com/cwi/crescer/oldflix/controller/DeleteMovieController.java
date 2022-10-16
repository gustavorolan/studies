package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.response.DeleteMovieResponse;
import br.com.cwi.crescer.oldflix.service.DeleteByIdMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class DeleteMovieController {
    @Autowired
    private DeleteByIdMovieService deleteByIdMovieService;

    @DeleteMapping("/{id}")
    public DeleteMovieResponse delete(@PathVariable Long id) {
        return deleteByIdMovieService.deleteById(id);
    }
}
