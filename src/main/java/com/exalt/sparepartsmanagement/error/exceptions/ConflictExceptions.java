package com.exalt.sparepartsmanagement.error.exceptions;

import com.exalt.sparepartsmanagement.error.ApiListExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class ConflictExceptions extends ApiListExceptions {

    private static final Logger l = LoggerFactory.getLogger(ConflictExceptions.class);

    public ConflictExceptions(String message) {

        super(message);
        l.info("ConflictExceptions class");

    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
