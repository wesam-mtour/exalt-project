package com.exalt.sparepartsmanagement.error.exceptions;

import com.exalt.sparepartsmanagement.error.ApiListExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class FileNotFoundExceptions extends ApiListExceptions {

    private static final Logger l = LoggerFactory.getLogger(FileNotFoundExceptions.class);
    public FileNotFoundExceptions(String message)
    {
        super(message);
        l.info("FileNotFoundExceptions class");

    }

    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;

    }
}
