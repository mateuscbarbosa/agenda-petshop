package br.com.agenda_petshop.application.exceptions;

public class GenerateTokenException extends RuntimeException{
    public GenerateTokenException(Throwable cause) {
        super("Erro ao criar token.", cause);
    }
}
