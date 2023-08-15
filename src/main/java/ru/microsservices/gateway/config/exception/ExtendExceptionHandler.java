package ru.microsservices.gateway.config.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExtendExceptionHandler {

    @ExceptionHandler(ExtendException.class)
    public ResponseEntity<ExceptionBody> handleMainException(ExtendException ex) {
        return ResponseEntity.status(ex.getError().getStatus())
                .body(new ExceptionBody(
                        ex.getError().getInternalCode(),
                        ex.getError().getStatus(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handleAnyException(Exception ex) {

        ExtendException extendException = ExtendException.of(ex);
        return ResponseEntity.status(extendException.
                getError().
                getStatus()
        ).body(new ExceptionBody(
                extendException.getError().getInternalCode(),
                extendException.getError().getStatus(),
                extendException.getMessage(),
                LocalDateTime.now()
        ));
    }
}
