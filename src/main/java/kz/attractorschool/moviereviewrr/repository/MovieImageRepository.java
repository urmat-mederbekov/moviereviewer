package kz.attractorschool.moviereviewrr.repository;

import kz.attractorschool.moviereviewrr.model.MovieImage;
import org.springframework.data.repository.CrudRepository;

public interface MovieImageRepository  extends CrudRepository<MovieImage, String> {
}
