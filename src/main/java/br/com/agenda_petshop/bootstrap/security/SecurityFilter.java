package br.com.agenda_petshop.bootstrap.security;

import br.com.agenda_petshop.application.usecases.auth.LoadUserUseCase;
import br.com.agenda_petshop.application.usecases.auth.RecoverSubjectFromTokenUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final RecoverSubjectFromTokenUseCase recoverSubject;
    private final LoadUserUseCase loadUserUseCase;

    public SecurityFilter(RecoverSubjectFromTokenUseCase recoverSubject, LoadUserUseCase loadUserUseCase) {
        this.recoverSubject = recoverSubject;
        this.loadUserUseCase = loadUserUseCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if(!token.isEmpty()){
            var email = recoverSubject.execute(token);
            var user = loadUserUseCase.loadUserByUsername(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request,response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")){
            return "";
        }
        return  authHeader.replace("Bearer ","");
    }
}
