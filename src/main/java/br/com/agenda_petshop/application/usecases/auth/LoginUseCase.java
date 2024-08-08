package br.com.agenda_petshop.application.usecases.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final LoadUserUseCase loadUserUseCase;
    private final GenerateTokenUseCase generateTokenUseCase;

    public LoginUseCase(AuthenticationManager authenticationManager, LoadUserUseCase loadUserUseCase, GenerateTokenUseCase generateTokenUseCase) {
        this.authenticationManager = authenticationManager;
        this.loadUserUseCase = loadUserUseCase;
        this.generateTokenUseCase = generateTokenUseCase;
    }

    public String execute (String email, String password){
        var authentication = new UsernamePasswordAuthenticationToken(email,password);

        this.authenticationManager.authenticate(authentication);

        final var user = this.loadUserUseCase.loadUserByUsername(email);
        return this.generateTokenUseCase.execute(user);
    }
}
