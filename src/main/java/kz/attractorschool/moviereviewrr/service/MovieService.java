package kz.attractorschool.moviereviewrr.service;

import kz.attractorschool.moviereviewrr.dto.MovieDTO;
import kz.attractorschool.moviereviewrr.exception.ResourceNotFoundException;
import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.repository.MovieImageRepository;
import kz.attractorschool.moviereviewrr.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieImageRepository movieImageRepository;
    private final MovieRepository movieRepository;

    public MovieService(MovieImageRepository movieImageRepository, MovieRepository movieRepository) {
        this.movieImageRepository = movieImageRepository;
        this.movieRepository = movieRepository;
    }

    public MovieDTO addMovie(MovieDTO movieData) {
        var movieImage = movieImageRepository.findById(movieData.getImageId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie Image with " + movieData.getImageId() + " doesn't exists!"));
        var movie = Movie.builder()
                .image(movieImage)
                .releaseYear(movieData.getYear())
                .title(movieData.getTitle())
                .actors(movieData.getActors())
                .directors(movieData.getDirectors())
                .build();

        movieRepository.save(movie);
        return MovieDTO.from(movie);
    }

    public Slice<MovieDTO> findMovies(Pageable pageable) {
        var slice = movieRepository.findAll(pageable);
        return slice.map(MovieDTO::from);
    }

    public MovieDTO findOne(String movieId) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find movie with the ID: " + movieId));
        return MovieDTO.from(movie);
    }
}
