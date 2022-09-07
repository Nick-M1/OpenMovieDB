package com.nick.movieapp.controller;


import com.nick.movieapp.service.MovieService;
import com.nick.movieapp.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path="api/v1/openmovie/search")
public class MovieController {

    private final MovieService movieService;
    private final SearchService searchService;

    public MovieController(MovieService movieService, SearchService searchService) {
        this.movieService = movieService;
        this.searchService = searchService;
    }

    @GetMapping("/title/{title}")
    public String searchByTitle(Model model, @PathVariable("title") String title) {
        model.addAttribute("search", searchService.searchByTitle(title));
        return "search-page";
    }


    @GetMapping("/imdbID/{imdbID}")
    public String searchByImdbID(Model model, @PathVariable("imdbID") String imdbID) {
        model.addAttribute("movie", movieService.searchByImdbID(imdbID));
//        System.out.println(model.getAttribute("Actors"));       // TODO REMOVE
        return "movie-view";
    }


}
