package de.omuehlnikel.tutorials.customerservice.exception;

import jakarta.validation.constraints.NotBlank;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
}
