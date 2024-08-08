package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.EntityNotFoundException;
import br.com.agenda_petshop.application.exceptions.NoUniqueValueException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SelfUpdateUserUseCase {
    private final UserRepository userRepository;

    public SelfUpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        final var userById = userRepository.findById(user.getId());
        final var userByEmail = userRepository.findByEmail(user.getEmail());

        if(userById.isEmpty())
            throw new EntityNotFoundException("Usuário não encontrado.");

        if((!user.getEmail().equals(userById.get().getEmail())) && userByEmail.isPresent())
            throw new NoUniqueValueException("Um usuário com esse e-mail já está cadastrado no banco");

        var userFound = userById.get();

        userFound.setEmail(user.getEmail());
        userFound.setName(user.getName());
        //TODO: precisa criar uma validação de senha com as regras
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        userFound.setPassword(encrypted);

        return userRepository.save(userFound);
    }
}
