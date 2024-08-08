package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.EntityNotFoundException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetUserPasswordUseCase {
    private final UserRepository userRepository;

    public ResetUserPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String id) {
        final var userById = userRepository.findById(id);

        if(userById.isEmpty())
            throw new EntityNotFoundException("Usuário não encontrado.");

        var userFound = userById.get();

        userFound.generatePassword(10);
        String encrypted = new BCryptPasswordEncoder().encode(userFound.getPassword());
        userFound.setPassword(encrypted);
        userFound.setFirstPassword(true);

        return this.userRepository.save(userFound);
    }
}
