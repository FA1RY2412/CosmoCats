package org.example.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  private Map<String, Object> body(HttpStatus status, String error, String message, String path) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", OffsetDateTime.now());
    map.put("status", status.value());
    map.put("error", error);
    map.put("message", message);
    map.put("path", path);
    return map;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex,
                                                              HttpServletRequest req) {
    String details = ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
        .collect(Collectors.joining("; "));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(body(HttpStatus.BAD_REQUEST, "Bad Request", details, req.getRequestURI()));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, Object>> handleConstraint(ConstraintViolationException ex,
                                                              HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(body(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), req.getRequestURI()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleOther(Exception ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(body(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), req.getRequestURI()));
  }
}
