package kz.attractorschool.moviereviewrr.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class MovieImageDTO {
    private String imageId;
}
