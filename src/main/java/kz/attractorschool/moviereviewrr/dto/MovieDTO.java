package kz.attractorschool.moviereviewrr.dto;

import kz.attractorschool.moviereviewrr.model.Movie;
import lombok.*;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class MovieDTO {

    public static MovieDTO from(Movie movie) {
        var movieImageId = movie.getImage() == null
                ? "-no-image-id"
                : movie.getImage().getId();

        return builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .actors(movie.getActors())
                .directors(movie.getDirectors())
                .year(movie.getReleaseYear())
                .rating(movie.getRating())
                .imageId(movieImageId)
                .build();
    }

    private String id;
    private String title;
    private int year;
    private String imageId;
    private double rating;
    private List<String> actors;
    private List<String> directors;

}
