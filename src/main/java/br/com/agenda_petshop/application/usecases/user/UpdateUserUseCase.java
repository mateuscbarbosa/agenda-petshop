package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.NoUniqueValueException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user){
        final var userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByEmail.isPresent())
            throw new NoUniqueValueException("Um usuário com esse e-mail já está cadastrado no banco");

        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encrypted);

        return this.userRepository.save(user);
    }
}
