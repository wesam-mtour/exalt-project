package com.exalt.sparepartsmanagement.error.exceptions;

import com.exalt.sparepartsmanagement.error.ApiListExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class NotFoundExceptions extends ApiListExceptions {

    private static final Logger l = LoggerFactory.getLogger(NotFoundExceptions.class);

    public NotFoundExceptions(String message) {
        super(message);
        l.info("NotFoundExceptions class");

    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;

    }
}

