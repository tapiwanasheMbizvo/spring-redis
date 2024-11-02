package com.tapiwa.demo.logging.controllers.controllerAdvice;

import com.tapiwa.demo.logging.controllers.response.HttpResponse;
import com.tapiwa.demo.logging.services.exceptions.PetServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PetControllerAdvice {



    @ExceptionHandler(PetServiceException.class)
    public ResponseEntity<HttpResponse> handlePetServiceException(PetServiceException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .message("Pet not found")
                .localDateTime(LocalDateTime.now())
                .build());
    }
}
