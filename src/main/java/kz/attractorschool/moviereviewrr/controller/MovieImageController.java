package kz.attractorschool.moviereviewrr.controller;

import kz.attractorschool.moviereviewrr.dto.MovieImageDTO;
import kz.attractorschool.moviereviewrr.service.MovieImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class MovieImageController {

    private final MovieImageService movieImageService;

    public MovieImageController(MovieImageService movieImageService) {
        this.movieImageService = movieImageService;
    }

    @PostMapping
    public MovieImageDTO addMovieImage(@RequestParam("file") MultipartFile file) {
        return movieImageService.addImage(file);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> serveFile(@PathVariable String imageId) {
        Resource resource = movieImageService.getById(imageId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(resource);
    }
}
