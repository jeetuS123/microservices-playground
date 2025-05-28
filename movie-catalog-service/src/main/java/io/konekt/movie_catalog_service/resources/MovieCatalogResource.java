package io.konekt.movie_catalog_service.resources;

import io.konekt.movie_catalog_service.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        return List.of(
                new CatalogItem("Inception", "A movie about dreams within dreams.", "****"),
                new CatalogItem("The Shawshank Redemption", "A story about hope and friendship.", "**"),
                new CatalogItem("The Dark Knight", "A superhero film featuring Batman.", "***")
        );
    }
}
