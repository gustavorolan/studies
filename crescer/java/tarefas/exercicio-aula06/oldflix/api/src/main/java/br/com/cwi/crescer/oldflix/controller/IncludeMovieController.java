package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.response.IncludeMovieIntoListResponse;
import br.com.cwi.crescer.oldflix.service.IncludeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class IncludeMovieController  {

    @Autowired
    private IncludeMovieService includeMovieService;

    @PostMapping
    public IncludeMovieIntoListResponse include(@Valid @RequestBody IncludeMovieIntoListRequest request) {
        return includeMovieService.include(request);
    }
}
