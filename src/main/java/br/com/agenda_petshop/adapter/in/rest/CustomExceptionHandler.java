package br.com.agenda_petshop.adapter.in.rest;

import br.com.agenda_petshop.adapter.in.rest.dtos.exception.BusinessException;
import br.com.agenda_petshop.application.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BusinessException notValidException(MethodArgumentNotValidException ex){
        return new BusinessException(
                HttpStatus.BAD_REQUEST,
                ex,
                ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public BusinessException userNotFound(UserNotFoundException ex){
        return new BusinessException(
                HttpStatus.NOT_FOUND,
                ex,
                List.of(ex.getMessage()));
    }

    @ExceptionHandler(NoUniqueEmailException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BusinessException noUniqueEmail(NoUniqueEmailException ex){
        return new BusinessException(
                HttpStatus.BAD_REQUEST,
                ex,
                List.of(ex.getMessage()));
    }

    @ExceptionHandler(PasswordValidationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BusinessException passwordValidation(PasswordValidationException ex){
        return new BusinessException(
                HttpStatus.BAD_REQUEST,
                ex,
                List.of(ex.getMessage()));
    }

    @ExceptionHandler(TokenValidationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BusinessException tokenValidation(TokenValidationException ex){
        return new BusinessException(
                HttpStatus.UNAUTHORIZED,
                ex,
                List.of(ex.getMessage()));
    }

    @ExceptionHandler(GenerateTokenException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BusinessException tokenValidation(GenerateTokenException ex){
        return new BusinessException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex,
                List.of(ex.getMessage()));
    }

    @ExceptionHandler(EmailSenderException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BusinessException emailSender(EmailSenderException ex){
        return new BusinessException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex,
                List.of(ex.getMessage()));
    }
}
