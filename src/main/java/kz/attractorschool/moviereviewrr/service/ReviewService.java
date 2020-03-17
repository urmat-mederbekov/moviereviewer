package kz.attractorschool.moviereviewrr.service;

import kz.attractorschool.moviereviewrr.dto.ReviewDTO;
import kz.attractorschool.moviereviewrr.model.Movie;
import kz.attractorschool.moviereviewrr.model.Review;
import kz.attractorschool.moviereviewrr.repository.ReviewRepository;
import kz.attractorschool.moviereviewrr.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public ReviewDTO addReview(/* From Spring Security! User user, */ReviewDTO reviewData) {
        //TODO one user can have only one review per movie. add check
        var user = userRepository.findByEmail("demo@demo").get();
        var review = Review.builder()
                .id(reviewData.getId())
                .movie(Movie.builder().id(reviewData.getMovieId()).build())
                .review(reviewData.getReview())
                .stars(reviewData.getStars())
                .reviewer(user)
                .build();
        reviewRepository.save(review);

        // TODO recalculate rating after save. Update movie document

        return ReviewDTO.from(review);
    }

    public Slice<ReviewDTO> findReviews(String movieId, Pageable pageable) {
        var slice = reviewRepository.findByMovieId(movieId, pageable);
        return slice.map(ReviewDTO::from);
    }

    public boolean deleteReview(String reviewId) {
        //TODO recalculate movie rating before delete
        reviewRepository.deleteById(reviewId);
        return true;
    }
}
