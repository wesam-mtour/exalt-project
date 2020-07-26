package com.exalt.sparepartsmanagement.error;


import com.exalt.sparepartsmanagement.error.exceptions.BadRequestExceptions;
import com.exalt.sparepartsmanagement.error.exceptions.ConflictExceptions;
import com.exalt.sparepartsmanagement.error.exceptions.FileNotFoundExceptions;
import com.exalt.sparepartsmanagement.error.exceptions.NotFoundExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // write global code shared to all controllers, handle exceptions across the whole application

public class ApiExceptionsHandler extends ResponseEntityExceptionHandler { // because ResponseEntityExceptionHandler has many handles
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionsHandler.class);

    @ExceptionHandler(NotFoundExceptions.class) // handling exceptions in specific handler
    public ResponseEntity<ErrorFeatures> handleApiException(NotFoundExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());//means Generic class, status
    }

    @ExceptionHandler(ConflictExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(ConflictExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ":" + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorFeatures apiError = new ErrorFeatures(errors.toString(), request.getDescription(false));
        return handleExceptionInternal(ex, apiError.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(FileNotFoundExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(FileNotFoundExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }
    /*
     handling the exception in specific method
     */
    @ExceptionHandler(BadRequestExceptions.class)
    public ResponseEntity<ErrorFeatures> handleApiException(BadRequestExceptions ex, WebRequest request) {
        logger.info("ApiExceptionsHandler class");
        ErrorFeatures error = new ErrorFeatures(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, ex.getStatus());
    }
}
