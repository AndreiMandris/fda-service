package com.example.fdaservice.api.exception;

import com.example.fdaservice.infrastructure.exception.ApplicationNotFoundException;
import com.example.fdaservice.infrastructure.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.NotFound.class)
  public ResponseEntity<Object> handleResourceNotFound(HttpClientErrorException.NotFound exception) {
    return new ResponseEntity<>(
        exception.getStatusText(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationNotFoundException.class)
  public ResponseEntity<Object> handleApplicationNotFound(ApplicationNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @org.springframework.web.bind.annotation.ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<Object> handleResourceAlreadyExists(ResourceAlreadyExistsException exception) {
    return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
  }

}
