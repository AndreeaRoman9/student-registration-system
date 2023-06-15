package backend.studentregistrationsystem.middlewares;

import backend.studentregistrationsystem.constants.SeverityConstants;
import backend.studentregistrationsystem.exceptions.ConflictException;
import backend.studentregistrationsystem.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ErrorMiddleware {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleException(ConflictException e) {
        return new ResponseEntity<>(
                Map.of(
                        "error", e.getMessage(),
                        "severity", SeverityConstants.ERROR
                ),
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException e) {
        return new ResponseEntity<>(
                Map.of(
                        "error", e.getMessage(),
                        "severity", SeverityConstants.ERROR
                ),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>(
                Map.of(
                        "error", e.getMessage(),
                        "severity", SeverityConstants.ERROR
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
