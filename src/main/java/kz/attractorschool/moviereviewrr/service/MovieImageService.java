package kz.attractorschool.moviereviewrr.service;

import kz.attractorschool.moviereviewrr.dto.MovieImageDTO;
import kz.attractorschool.moviereviewrr.exception.ResourceNotFoundException;
import kz.attractorschool.moviereviewrr.model.MovieImage;
import kz.attractorschool.moviereviewrr.repository.MovieImageRepository;
import org.bson.types.Binary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MovieImageService {
    private final MovieImageRepository movieImageRepository;

    public MovieImageService(MovieImageRepository movieImageRepository) {
        this.movieImageRepository = movieImageRepository;
    }

    public MovieImageDTO addImage(MultipartFile file) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.length == 0) {
            // TODO return no content or something or throw exception
            //  which will be processed on controller layer
        }

        Binary bData = new Binary(data);
        MovieImage image = MovieImage.builder().posterData(bData).build();

        movieImageRepository.save(image);

        return MovieImageDTO.builder()
                .imageId(image.getId())
                .build();
    }

    public Resource getById(String imageId) {
        MovieImage movieImage = movieImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie Image with " + imageId + " doesn't exists!"));
        return new ByteArrayResource(movieImage.getPosterData().getData());
    }
}
