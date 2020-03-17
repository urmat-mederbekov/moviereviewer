package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.annotations.ApiPageable;
import kz.attractorschool.moviereviewrr.dto.MovieDTO;
import kz.attractorschool.moviereviewrr.dto.ReviewDTO;
import kz.attractorschool.moviereviewrr.service.MovieService;
import kz.attractorschool.moviereviewrr.service.ReviewService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final ReviewService reviewService;
    private final MovieService movieService;

    public MovieController(ReviewService reviewService, MovieService movieService) {
        this.reviewService = reviewService;
        this.movieService = movieService;
    }

    @ApiPageable
    @GetMapping
    public Slice<MovieDTO> findMovies(@ApiIgnore Pageable pageable) {
        return movieService.findMovies(pageable);
    }

    @GetMapping("/{movieId}")
    public MovieDTO getMovie(@PathVariable String movieId) {
        return movieService.findOne(movieId);
    }

    @ApiPageable
    @GetMapping("/{movieId}/reviews")
    public Slice<ReviewDTO> findReviews(@PathVariable String movieId, @ApiIgnore Pageable pageable) {
        return reviewService.findReviews(movieId, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MovieDTO addMovie(@RequestBody MovieDTO movieData) {
        return movieService.addMovie(movieData);
    }
}
