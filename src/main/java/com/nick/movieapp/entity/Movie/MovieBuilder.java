package com.nick.movieapp.entity.Movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MovieBuilder {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private String title;
    private MOVIE_RATED rated;
    private LocalDate releaseDate;
    private String[] genres;
    private String type;
    private String director;
    private String[] writers;
    private String[] actors;
    private String plot;
    private String languages;
    private String countries;
    private String posterURL;
    private HashMap<String, String> ratings;


    public MovieBuilder() {
        this.ratings = new HashMap<>();
    }

    // SETTERS:
    public MovieBuilder addTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder addRated(String ratedStr) {
        this.rated = switch (ratedStr) {
            case "G"     -> MOVIE_RATED.G;
            case "PG"    -> MOVIE_RATED.PG;
            case "PG-13" -> MOVIE_RATED.PG13;
            case "R"     -> MOVIE_RATED.R;
            case "NC-17" -> MOVIE_RATED.NC17;
            case "TV-MA" -> MOVIE_RATED.TVMA;
            default -> throw new IllegalStateException("Unknown rating");
        };
        return this;
    }

    public MovieBuilder addReleaseDate(String releaseDateStr) {
        this.releaseDate = LocalDate.parse(releaseDateStr, dateTimeFormatter);
        return this;
    }

    public MovieBuilder addGenres(String genresStr) {
        this.genres = genresStr.split(", ");
        return this;
    }

    public MovieBuilder addType(String type) {
        this.type = type;
        return this;
    }

    public MovieBuilder addDirector(String director) {
        this.director = director;
        return this;
    }

    public MovieBuilder addWriters(String writersStr) {
        this.writers = writersStr.split(", ");
        return this;
    }

    public MovieBuilder addActors(String actorsStr) {
        this.actors = actorsStr.split(", ");
        System.out.println(Arrays.toString(actors));            // TODO: REMOVE
        return this;
    }

    public MovieBuilder addPlot(String plot) {
        this.plot = plot;
        return this;
    }

    public MovieBuilder addLanguages(String languages) {
        this.languages = languages;
        return this;
    }

    public MovieBuilder addCountries(String countries) {
        this.countries = countries;
        return this;
    }

    public MovieBuilder addPosterURL(String posterURL) {
        this.posterURL = posterURL;
        return this;
    }

    public MovieBuilder addRatings(JsonNode ratings, String imdbRating) {
        List<HashMap<String, String>> converted = new ObjectMapper().convertValue(ratings, new TypeReference<List<HashMap<String, String>>>(){} );
        converted.forEach(map -> this.ratings.put(map.get("Source"), map.get("Value")));

        this.ratings.put("Imbd", imdbRating);
        return this;
    }



    // GETTERS:
    public String getTitle() {
        return title;
    }

    public MOVIE_RATED getRated() {
        return rated;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getType() {
        return type;
    }

    public String getDirector() {
        return director;
    }

    public String[] getWriters() {
        return writers;
    }

    public String[] getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguages() {
        return languages;
    }

    public String getCountries() {
        return countries;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public HashMap<String, String> getRatings() {
        return ratings;
    }



    // Finally, construct a myObject instance using this myObjectBuilder instance
    public Movie build() {
        return new Movie(this);
    }
}
