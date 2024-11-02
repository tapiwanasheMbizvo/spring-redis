package com.tapiwa.demo.logging.services.exceptions;

public class PetServiceException extends RuntimeException {
    public PetServiceException(String message) {
        super(message);
    }
}
