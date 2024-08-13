package br.com.agenda_petshop.application.exceptions;

public class EmailException extends RuntimeException{
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
