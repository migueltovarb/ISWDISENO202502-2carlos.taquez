package com.miempresa.vehiculosapi.exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> formatFieldError(fieldError))
                .collect(Collectors.toList());

    ApiError apiError = new ApiError();
    apiError.setTimestamp(Instant.now());
    apiError.setStatus(HttpStatus.BAD_REQUEST.value());
    apiError.setMessage("Error de validación");
    apiError.setErrors(errors);
    return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ApiError> handleVehicleNotFound(VehicleNotFoundException ex) {
    ApiError apiError = new ApiError();
    apiError.setTimestamp(Instant.now());
    apiError.setStatus(HttpStatus.NOT_FOUND.value());
    apiError.setMessage(ex.getMessage());
    apiError.setErrors(List.of());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
    ApiError apiError = new ApiError();
    apiError.setTimestamp(Instant.now());
    apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    apiError.setMessage("Ha ocurrido un error inesperado. Intenta más tarde.");
    apiError.setErrors(List.of());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    private String formatFieldError(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}

