package com.tapiwa.demo.logging.controllers.controllerAdvice;

import com.tapiwa.demo.logging.controllers.response.HttpResponse;
import com.tapiwa.demo.logging.services.exceptions.CountryServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CountryControllerAdvice {


    @ExceptionHandler(CountryServiceException.class)
    public ResponseEntity<HttpResponse> handleCountryServiceException(CountryServiceException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Country not found")
                .localDateTime(LocalDateTime.now())
                .build());
    }
}
