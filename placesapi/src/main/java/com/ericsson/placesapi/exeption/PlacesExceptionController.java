package com.ericsson.placesapi.exeption;

import com.ericsson.placesapi.exeption.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class PlacesExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(NotFoundException exception,
                                                                            WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .details(webRequest.getDescription(false))
                        .time(new Date())
                        .build()
        );
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ExceptionMessage> handleNumberFormatException(NumberFormatException exception,
                                                                        WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .details(webRequest.getDescription(false))
                        .time(new Date())
                        .build()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionMessage> handleMissingRequestParameterException(MissingServletRequestParameterException exception,
                                                                                   WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .details(webRequest.getDescription(false))
                        .time(new Date())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleGlobalException(Exception exception, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .details(webRequest.getDescription(false))
                        .time(new Date())
                        .build()
        );
    }
}
