package com.bbeerbear.grpcflix.aggregator.controller;

import com.bbeerbear.grpcflix.aggregator.dto.RecommendedMovie;
import com.bbeerbear.grpcflix.aggregator.dto.UserGenre;
import com.bbeerbear.grpcflix.aggregator.service.UserMovieService;
import com.bbeerbear.grpcflix.common.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AggregatorController {
    @Autowired
    private UserMovieService userMovieService;
    @GetMapping("/users/{loginId}")
    public List<RecommendedMovie> getMovies(@PathVariable String loginId){
        return this.userMovieService.getUserMovieSuggestions(loginId);
    }
    @PutMapping("/users")
    public void setUserGenre(@RequestBody UserGenre userGenre){
        this.userMovieService.setUserGenre(userGenre);
    }
}
