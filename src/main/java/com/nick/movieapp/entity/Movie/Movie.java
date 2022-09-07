package com.nick.movieapp.entity.Movie;

import java.time.LocalDate;
import java.util.HashMap;

public class Movie {

    private final String title;
    private final MOVIE_RATED rated;
    private final LocalDate releaseDate;
    private final String[] genres;
    private final String type;
    private final String director;
    private final String[] writers;
    private final String[] actors;
    private final String plot;
    private final String languages;
    private final String countries;

    private final String posterURL;
    private final HashMap<String, String> ratings;    // Key: "Source", Value: "Rating value"


    public Movie(MovieBuilder builder) {
        this.title = builder.getTitle();
        this.rated = builder.getRated();
        this.releaseDate = builder.getReleaseDate();
        this.genres = builder.getGenres();
        this.type = builder.getType();
        this.director = builder.getDirector();
        this.writers = builder.getWriters();
        this.actors = builder.getActors();
        this.plot = builder.getPlot();
        this.languages = builder.getLanguages();
        this.countries = builder.getCountries();
        this.posterURL = builder.getPosterURL();
        this.ratings = builder.getRatings();
    }

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



    static public MovieBuilder builder() {
        return new MovieBuilder();
    }
}
