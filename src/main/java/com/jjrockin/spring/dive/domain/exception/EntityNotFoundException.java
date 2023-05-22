package com.jjrockin.spring.dive.domain.exception;

public class EntityNotFoundException extends BusinessRulesException {

    public static final long serialVersionUID = 1L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
