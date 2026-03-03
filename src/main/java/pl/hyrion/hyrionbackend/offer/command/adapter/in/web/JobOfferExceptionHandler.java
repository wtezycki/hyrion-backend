package pl.hyrion.hyrionbackend.offer.command.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidJobOfferException;
import pl.hyrion.hyrionbackend.offer.command.domain.exception.InvalidSalaryException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class JobOfferExceptionHandler {

    @ExceptionHandler(InvalidJobOfferException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJobOfferException(InvalidJobOfferException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(InvalidSalaryException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSalaryException(InvalidSalaryException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    public record ErrorResponse(
            int status,
            String message,
            LocalDateTime timestamp
    ) {}
}
