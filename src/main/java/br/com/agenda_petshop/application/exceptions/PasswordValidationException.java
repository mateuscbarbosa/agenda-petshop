package br.com.agenda_petshop.application.exceptions;

public class PasswordValidationException extends RuntimeException{
    public PasswordValidationException() {
        super("A senha deve ter pelo menos 8 caracteres, " +
                "contendo pelo menos 2 letras maiúsculas, 2 letras minúsculas, 2 números, " +
                "e 1 dos seguintes símbolos: !@#$%&*_+-");
    }
}
