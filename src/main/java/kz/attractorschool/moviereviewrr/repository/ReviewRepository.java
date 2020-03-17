package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, String> {

    @Query(" { '$or' : [ { 'reviewer.id' : ?0 }, { 'movie.id' : ?1 } ] } ")
    List<Review> findByReviewerOrMovie(String reviewerId, String movieId);

    boolean existsByMovieIdAndReviewer_Id(String movieId, String reviewerId);

    Slice<Review> findByMovieId(String movieId, Pageable pageable);
}
