package com.example.fdaservice.infrastructure.exception;

public class ApplicationNotFoundException extends RuntimeException {
  public ApplicationNotFoundException(String message) {
    super(message);
  }
}
