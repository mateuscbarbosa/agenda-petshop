package br.com.agenda_petshop.application.exceptions;

public class NoUniqueEmailException extends RuntimeException{

    public NoUniqueEmailException() {
        super("Um usuário com esse e-mail já está cadastrado no banco");
    }
}
