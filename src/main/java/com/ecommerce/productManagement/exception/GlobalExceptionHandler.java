package com.ecommerce.productManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a centralized exception handler for managing
 * exceptions globally in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method catches the {@link ProductAlreadyExistsException} thrown by the application
     * when attempting to create a product that already exists.
     */
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    /**
     * This method catches the {@link ProductNotFoundException} thrown by the application
     * when a product is not found by its ID.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    /**
     * This method catches {@link MethodArgumentNotValidException}, which occurs when the request body
     * fails to meet the validation constraints (e.g., a missing required field or an invalid value).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> errorMessages = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }
}