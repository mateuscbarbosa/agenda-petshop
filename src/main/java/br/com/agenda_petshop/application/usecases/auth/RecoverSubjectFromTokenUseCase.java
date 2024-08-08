package br.com.agenda_petshop.application.usecases.auth;

import br.com.agenda_petshop.application.exceptions.TokenValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RecoverSubjectFromTokenUseCase {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.issuer}")
    private String issuer;

    public String execute (String token){
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new TokenValidationException("Token inválido ou expirado! " + e.getMessage());
        }
    }
}
