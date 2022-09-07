package com.nick.movieapp.exceptions;

public class PageNotFound extends GeneralException {

    public PageNotFound(String message) {
        super(message);
    }

    public PageNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PageNotFound(Throwable cause) {
        super(cause);
    }

    public PageNotFound(){}
}
