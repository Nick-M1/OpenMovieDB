package com.nick.movieapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nick.movieapp.entity.Movie.Movie;
import com.nick.movieapp.entity.Search.Search;
import com.nick.movieapp.service.httperrors.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class SearchService {

    private static final String OPENMOVIE_URL = "https://www.omdbapi.com/?s={title}&apikey={key}";

    @Value("${api.openmovie.key}")         // This value is in resources.  application.properties
    private String apiKey;


    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SearchService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.objectMapper = objectMapper;
    }

    public Search searchByTitle(String name) {
        URI url = new UriTemplate(OPENMOVIE_URL).expand(name, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return convert(response);
    }

    private Search convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return new Search(root.path("Search"));

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }



}
