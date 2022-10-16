package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.DevolutionMovieResponse;
import br.com.cwi.crescer.oldflix.service.DevolutionMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class DevolutionMovieController  {
    @Autowired
    private DevolutionMovieService devolutionMovieService;

    @PutMapping("/{id}/devolver")
    public DevolutionMovieResponse devolution(@Valid @RequestBody DevolutionMovieRequest request, @PathVariable Long id) {
        return devolutionMovieService.devolution(id, request);
    }
}
