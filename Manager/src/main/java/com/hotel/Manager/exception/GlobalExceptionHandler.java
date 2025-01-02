package com.hotel.Manager.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(NoRoomsFoundException.class)
    public ResponseEntity<String> handleNoRoomsFoundException(NoRoomsFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> handleRoomNotFoundException(RoomNotFoundException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Handle MethodArgumentNotValidException (Validation errors on @Valid object)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        StringBuilder errorMessages = new StringBuilder("Validation failed: ");

        for (FieldError error : ex.getBindingResult().getFieldErrors())
        {
            errorMessages.append(error.getField())
                            .append(" - ")
                            .append(error.getDefaultMessage())
                            .append(".");
        }

        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }

    //Handle ConstraintViolationException (Validation errors on constraints)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex)
    {
        StringBuilder errorMessages = new StringBuilder("Validation failed: ");
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        for (ConstraintViolation<?> violation : violations)
        {
            errorMessages.append(violation.getPropertyPath().toString())
                            .append(" - ")
                            .append(violation.getMessage())
                            .append(".");
        }

        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }

    //Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex)
    {
        return new ResponseEntity<>("<h1>Error: " + ex.getMessage() + "</h1>", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}