package com.exalt.sparepartsmanagement.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public abstract class ApiListExceptions extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(ApiListExceptions.class);

    public ApiListExceptions(String message) {
        super(message);
        logger.info("ApiListExceptions class");
    }
    public abstract HttpStatus getStatus();
}

