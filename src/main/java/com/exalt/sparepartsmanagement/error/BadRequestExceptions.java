package com.exalt.sparepartsmanagement.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class BadRequestExceptions extends ApiListExceptions {

    private static final Logger l = LoggerFactory.getLogger(BadRequestExceptions.class);

    public BadRequestExceptions(String message) {
        super(message);
        l.info("BadRequestExceptions class");


    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }


}
