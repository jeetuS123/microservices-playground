package io.konekt.movie_catalog_service.resources;

import io.konekt.movie_catalog_service.models.CatalogItem;
import io.konekt.movie_catalog_service.models.Movie;
import io.konekt.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<Rating> ratings= Arrays.asList(
                new Rating("123","5"),
                new Rating("45","4"),
                new Rating("67","3")
        );
        return ratings.stream().map(rating->{
             Movie movie=restTemplate.getForObject("http://localhost:8082/movies/"+rating.getId(),Movie.class);
             return new CatalogItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());


    }
}
