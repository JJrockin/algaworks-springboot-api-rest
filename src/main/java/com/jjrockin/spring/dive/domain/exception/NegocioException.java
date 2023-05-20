package com.jjrockin.spring.dive.domain.exception;

public class NegocioException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public NegocioException(String message) {
        super(message);
    }
}
