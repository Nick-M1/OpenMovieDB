package com.nick.movieapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.movieapp.entity.Movie.Movie;
import com.nick.movieapp.service.httperrors.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class MovieService {

    private static final String OPENMOVIE_URL = "https://www.omdbapi.com/?i={imdbID}&plot=full&apikey={key}";

    @Value("${api.openmovie.key}")         // This value is in resources.  application.properties
    private String apiKey;


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MovieService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.objectMapper = objectMapper;
    }

    public Movie searchByImdbID(String imdbID) {
        URI url = new UriTemplate(OPENMOVIE_URL).expand(imdbID, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return convert(response);
    }

    private Movie convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            System.out.println(root.path("Genre").asText());          // TODO REMOVE
            System.out.println(root.path("Actors").asText());

            return Movie.builder()
                    .addTitle(root.path("Title").asText())
                    .addRated(root.path("Rated").asText())
                    .addReleaseDate(root.path("Released").asText())
                    .addGenres(root.path("Genre").asText())
                    .addDirector(root.path("Director").asText())
                    .addWriters(root.path("Writers").asText())
                    .addActors(root.path("Actors").asText())
                    .addPlot(root.path("Plot").asText())
                    .addLanguages(root.path("Language").asText())
                    .addCountries(root.path("Country").asText())
                    .addPosterURL(root.path("Poster").asText())
                    .addRatings(root.path("Ratings"), root.path("imdbRating").asText())
                    .addType(root.path("Type").asText())
                    .build();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }



}
