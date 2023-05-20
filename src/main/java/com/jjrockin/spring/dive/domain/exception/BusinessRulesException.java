package com.jjrockin.spring.dive.domain.exception;

public class BusinessRulesException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public BusinessRulesException(String message) {
        super(message);
    }
}
