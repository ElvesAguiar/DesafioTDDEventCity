package com.devsuperior.demo.cotrollers.exceptions;

import com.devsuperior.demo.cotrollers.exceptions.StandartError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandartError> handleCityDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {

        StandartError error = new StandartError();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(Instant.now());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(request.getContextPath()).build().toUri();
        error.setPath(uri.getPath());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> handleCityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        StandartError error = new StandartError();
        error.setError(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(Instant.now());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(request.getContextPath()).build().toUri();
        error.setPath(uri.getPath());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
