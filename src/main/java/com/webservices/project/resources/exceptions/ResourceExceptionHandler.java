package com.webservices.project.resources.exceptions;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.webservices.project.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception,
      HttpServletRequest request) {
    StandardError standardError = new StandardError();
    standardError.setTimestamp(Instant.now());
    standardError.setStatus(HttpStatus.NOT_FOUND.value());
    standardError.setError(exception.getMessage());
    standardError.setMessage("Resource not found.");
    standardError.setPath(request.getRequestURI());
    return ResponseEntity.status(standardError.getStatus()).body(standardError);
  }
}
