package com.example.demo.config;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GreetingControllerAdvice {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ProblemDetail> handleBadRequest(IllegalArgumentException ex, HttpServletRequest httpServletRequest) {
    httpServletRequest.getAttributeNames().asIterator().forEachRemaining(att -> System.out.println("attribute name is " + att));
    return ResponseEntity.of(Optional.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(402), ex.getLocalizedMessage())));
  }
}
