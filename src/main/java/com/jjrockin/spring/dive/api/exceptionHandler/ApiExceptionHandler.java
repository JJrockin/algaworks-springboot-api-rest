package com.jjrockin.spring.dive.api.exceptionHandler;

import com.jjrockin.spring.dive.domain.exception.BusinessRulesException;
import com.jjrockin.spring.dive.domain.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problem.FieldToBeListed> fieldsToBeListed = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()) {

            String name = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldsToBeListed.add(new Problem.FieldToBeListed(name, message));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle("One or more fields invalid");
        problem.setFieldsToBeListed(fieldsToBeListed);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
    @ExceptionHandler(BusinessRulesException.class)
    public ResponseEntity<Object> handleBusinessRules(BusinessRulesException ex, WebRequest request){
        HttpStatus status  = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

}
