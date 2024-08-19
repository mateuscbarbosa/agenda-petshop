package br.com.agenda_petshop.application.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String element) {
        super(String.format("Usuário não encontrando com %s informado", element));
    }
}
