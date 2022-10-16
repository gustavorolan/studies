package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.RentMovieResponse;
import br.com.cwi.crescer.oldflix.service.RentMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/movies")

public class RentMovieController  {
    @Autowired
    private RentMovieService rentMovieService;

    @PutMapping("/{id}/alugar")
    public RentMovieResponse rent(@Valid @RequestBody RentMovieRequest request, @PathVariable Long id) {

        return rentMovieService.rent(id, request);
    }
}
