package io.konekt.movie_catalog_service.resources;

import io.konekt.movie_catalog_service.models.CatalogItem;
import io.konekt.movie_catalog_service.models.Movie;
import io.konekt.movie_catalog_service.models.Rating;
import io.konekt.movie_catalog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating=restTemplate.getForObject("http://rating-data-service/ratings/users/"+userId,UserRating.class);
        return userRating.getRatings().stream().map(rating -> {
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getId(),Movie.class);
            return new CatalogItem(movie.getName(), "desc", rating.getRating());
        }).collect(Collectors.toList());


    }
}


//Movie movie = webClientBuilder.build().get().uri("http://movie-info-service/movies/" + rating.getId())
//        .retrieve()
//        .bodyToMono(Movie.class)
//        .block();