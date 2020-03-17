package kz.attractorschool.moviereviewrr.dto;

import kz.attractorschool.moviereviewrr.model.Review;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ReviewDTO {
    public static ReviewDTO from(Review review) {
        return builder()
                .id(review.getId())
                .stars(review.getStars())
                .review(review.getReview())
                .movieId(review.getMovie().getId())
                .build();
    }

    @Builder.Default
    private String id = null;

    private int stars;
    private String movieId;
    private String review;


    // можно добавить сюда
    // например
    // краткую инфу по пользователю кто оставлял отзыв

    // но пока оставим минимум
}
