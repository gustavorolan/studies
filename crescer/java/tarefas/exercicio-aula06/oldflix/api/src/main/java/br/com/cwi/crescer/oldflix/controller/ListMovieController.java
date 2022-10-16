package br.com.cwi.crescer.oldflix.controller;

import br.com.cwi.crescer.oldflix.controller.response.ListMovieResponse;
import br.com.cwi.crescer.oldflix.service.ListMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class ListMovieController {
    @Autowired
    private ListMovieService listMovieService;

    @GetMapping
    public List<ListMovieResponse> list() {
        return listMovieService.list();
    }
}
