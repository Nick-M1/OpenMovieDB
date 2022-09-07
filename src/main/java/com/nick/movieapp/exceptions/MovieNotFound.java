package com.nick.movieapp.exceptions;

public class MovieNotFound extends GeneralException {

    public MovieNotFound(String message) {
        super(message);
    }

    public MovieNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFound(Throwable cause) {
        super(cause);
    }

    public MovieNotFound(){}
}
