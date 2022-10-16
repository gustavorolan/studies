package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.response.ChangeMovieByIdResponse;
import br.com.cwi.crescer.oldflix.service.ChangeMovieService;
import br.com.cwi.crescer.oldflix.service.IncludeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class ChangeMovieController {
    @Autowired
    private ChangeMovieService changeMovieService;

    @PutMapping("/{id}")
    public ChangeMovieByIdResponse changeMovie(@Valid @RequestBody ChangeMovieByIdRequest request, @PathVariable Long id) {
        return changeMovieService.change(id, request);
    }
}
