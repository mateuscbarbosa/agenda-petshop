package br.com.agenda_petshop.application.exceptions;

public class GenerateTokenException extends RuntimeException{
    public GenerateTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
