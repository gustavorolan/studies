package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.request.ChangeMovieByIdRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolutionMovieRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncludeMovieIntoListRequest;
import br.com.cwi.crescer.oldflix.controller.request.RentMovieRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<ListMovieResponse> list(){
        return movieService.list();
    }

    @PostMapping
    public IncludeMovieIntoListResponse  include(@Valid @RequestBody IncludeMovieIntoListRequest request){
        return movieService.include(request);
    }
    @PutMapping("/{id}/alugar")
    public RentMovieResponse rent(@Valid @RequestBody RentMovieRequest request, @PathVariable Long id){

        return movieService.rent(id,request);
    }

    @PutMapping("/{id}/devolver")
    public DevolutionMovieResponse devolution(@Valid  @RequestBody DevolutionMovieRequest request, @PathVariable Long id){
        return movieService.devolution(id,request);
    }
    @PutMapping("/{id}")
    public ChangeMovieByIdResponse changeMovie (@Valid  @RequestBody ChangeMovieByIdRequest request, @PathVariable Long id){
        return movieService.change(id,request);
    }
    @GetMapping("/{id}")
    public MovieByIdResponse movieById ( @PathVariable Long id){
        return movieService.movieByid(id);
    }
    @DeleteMapping ("/{id}")
    public DeleteMovieResponse delete( @PathVariable Long id){
        return movieService.deleteById(id);
    }

}
