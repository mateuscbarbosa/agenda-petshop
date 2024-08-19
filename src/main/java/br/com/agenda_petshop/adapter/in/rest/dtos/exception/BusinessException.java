package br.com.agenda_petshop.adapter.in.rest.dtos.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BusinessException {
    private HttpStatus status;
    private Throwable cause;
    private List<String> errors;

    public BusinessException() {
    }

    public BusinessException(HttpStatus status, Throwable cause, List<String> errors) {
        this.status = status;
        this.cause = cause;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public BusinessException setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public Throwable getCause() {
        return cause;
    }

    public BusinessException setCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public BusinessException setErrors(List<String> errors) {
        this.errors = errors;
        return this;
    }
}
