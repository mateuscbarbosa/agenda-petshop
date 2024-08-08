package br.com.agenda_petshop.application.usecases.auth;

import br.com.agenda_petshop.application.exceptions.EntityNotFoundException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoadUserUseCase implements UserDetailsService {
    private final UserRepository userRepository;

    public LoadUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = this.userRepository.findByEmail(username);

        if(user.isEmpty())
            throw new EntityNotFoundException("Usuário não encontrado.");

        return user.get();
    }
}
