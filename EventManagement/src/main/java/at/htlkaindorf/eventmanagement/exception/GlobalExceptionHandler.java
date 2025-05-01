package at.htlkaindorf.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParticipationException.class)
    public ResponseEntity<ErrorResponse> handleParticipationException(
            ParticipationException ex
    ) {
        ErrorResponse error = ErrorResponse
                .builder(
                        ex,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                ).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StillInUseException.class)
    public ResponseEntity<ErrorResponse> handleStillInUseException(
            StillInUseException ex
    ) {
        ErrorResponse error = ErrorResponse
                .builder(
                        ex,
                        HttpStatus.CONFLICT,
                        ex.getMessage()
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
