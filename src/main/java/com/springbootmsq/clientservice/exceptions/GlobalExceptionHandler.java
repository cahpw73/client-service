package com.springbootmsq.clientservice.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springbootmsq.clientservice.dtos.ApiError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    String message = ex.getBindingResult().getFieldErrors().stream()
        .map(err -> err.getField() + ": " + err.getDefaultMessage())
        .findFirst()
        .orElse("Validation failed");

    log.warn("Validation error at {} - {}", request.getRequestURI(), message);
    return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, message, request.getRequestURI());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex,
      HttpServletRequest request) {
    log.warn("Constraint violation at {} - {}", request.getRequestURI(), ex.getMessage());
    return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
    log.error("Unhandled exception at {} - {}", request.getRequestURI(), ex.getMessage(), ex);
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
    log.info("Resource not found at {} - {}", request.getRequestURI(), ex.getMessage());
    return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiError> handleNoHandlerFound(NoHandlerFoundException ex, HttpServletRequest request) {
    log.info("No handler found for {}", request.getRequestURI());
    return buildErrorResponse(HttpStatus.NOT_FOUND, "Endpoint not found", request.getRequestURI());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiError> handleBusinessException(BusinessException ex, HttpServletRequest request) {
    log.warn("Business rule violation at {} - {}", request.getRequestURI(), ex.getMessage());
    return buildErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), request.getRequestURI());
  }

  private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message, String path) {
    ApiError error = ApiError.builder()
        .timestamp(LocalDateTime.now())
        .status(status.value())
        .error(status.getReasonPhrase())
        .message(message)
        .path(path)
        .build();

    return new ResponseEntity<>(error, status);
  }
}
