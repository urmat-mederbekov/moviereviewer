package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.dto.ReviewDTO;
import kz.attractorschool.moviereviewrr.service.ReviewService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewData /*, From Spring Security! User user, */) {
        return reviewService.addReview( /*user, */ reviewData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        if (reviewService.deleteReview(id))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
