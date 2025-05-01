package at.htlkaindorf.springextended.exception;

import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {
        ErrorResponse error = ErrorResponse
                .builder(
                        ex,
                        HttpStatus.NOT_FOUND,
                        ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        ErrorResponse error = ErrorResponse
                .builder(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                ).build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
