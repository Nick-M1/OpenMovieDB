package com.nick.movieapp.service.httperrors;

import com.nick.movieapp.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;


/** Issue: RestTemplate throws error whenever Http status 4xx or 5xx is returned <p>
 *  Solution: Error handling by using a handler on ResponseTemplate */

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)
            throw new ServerSideException();

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            switch (httpResponse.getStatusCode()) {
                case BAD_REQUEST -> throw new MovieNotFound("This movie can not be found in database");
                case UNAUTHORIZED -> throw new InvalidApiKey("The API Key used was invalid");
                case NOT_FOUND -> throw new PageNotFound("Page not found");
                default -> throw new GeneralException(
                        String.format("Error code: %s \nError text: %s\n", httpResponse.getStatusCode(), httpResponse.getStatusText())
                );
            }
        }
    }
}