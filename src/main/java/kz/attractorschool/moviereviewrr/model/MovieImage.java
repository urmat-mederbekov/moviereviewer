package kz.attractorschool.moviereviewrr.model;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection="movie_images")
public class MovieImage {
    public static final MovieImage NO_IMAGE = MovieImage.builder().id("-NO-IMAGE-").build();
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    // здесь будем сохранять картинку для фильма
    @Builder.Default
    private Binary posterData = new Binary(new byte[0]);
}
