package kz.attractorschool.moviereviewrr.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection="movies")
public class Movie {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String title;
    private String poster;
    private int releaseYear;

    @DBRef
    private MovieImage image;

    @Builder.Default
    private List<String> directors = new ArrayList<>();

    @Builder.Default
    private List<String> actors = new ArrayList<>();

    // I think rating should be calculated upon adding a review and updated in the document
    // think service layer or even better move to separate consumer to calculate and update (queues)
    @Indexed
    private double rating;
}
