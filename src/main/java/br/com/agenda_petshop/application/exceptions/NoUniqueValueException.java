package br.com.agenda_petshop.application.exceptions;

public class NoUniqueValueException extends RuntimeException{

    public NoUniqueValueException(String message) {
        super(message);
    }
}
