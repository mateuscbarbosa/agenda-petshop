package br.com.agenda_petshop.application.exceptions;

public class TokenValidationException extends RuntimeException{
    public TokenValidationException(String message) {
        super(message);
    }
}
