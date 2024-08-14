package br.com.agenda_petshop.application.exceptions;

public class PasswordValidationException extends RuntimeException{
    public PasswordValidationException(String message) {
        super(message);
    }
}
