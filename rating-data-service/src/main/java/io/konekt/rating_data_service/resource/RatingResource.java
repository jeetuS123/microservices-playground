package io.konekt.rating_data_service.resource;

import io.konekt.rating_data_service.models.Rating;
import io.konekt.rating_data_service.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/ratings")
public class RatingResource {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, "5");
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("123", "5"),
                new Rating("45", "4"),
                new Rating("67", "3")
        );
        UserRating userRating = new UserRating(ratings);
        return userRating;
    }
}
