package br.com.agenda_petshop.application.usecases.auth;

import br.com.agenda_petshop.application.exceptions.GenerateTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class GenerateTokenUseCase {
    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.issuer}")
    private String issuer;

    @Value("${app.jwt.expiration}")
    private long expiration;

    public String execute (UserDetails user){
        try{
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(expirationDate())
                    .withSubject(user.getUsername())
                    .sign(Algorithm.HMAC256(secret));

        }catch (JWTCreationException e){
            throw new GenerateTokenException(e);
        }
    }

    private Instant expirationDate() {
        return Instant.now().plusSeconds(expiration);
    }
}
