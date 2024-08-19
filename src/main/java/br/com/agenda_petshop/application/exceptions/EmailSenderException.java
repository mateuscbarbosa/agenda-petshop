package br.com.agenda_petshop.application.exceptions;

public class EmailSenderException extends RuntimeException{
    public EmailSenderException(Throwable cause) {
        super("Erro ao tentar enviar email.", cause);
    }
}
