package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.UserNotFoundException;
import br.com.agenda_petshop.application.exceptions.NoUniqueEmailException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String id, User user){
        final var userById = userRepository.findById(id);
        final var userByEmail = userRepository.findByEmail(user.getEmail());

        if(userById.isEmpty())
            throw new UserNotFoundException(id);

        if((!user.getEmail().equals(userById.get().getEmail())) && userByEmail.isPresent())
            throw new NoUniqueEmailException();

        var userFound = userById.get();

        userFound.setEmail(user.getEmail());
        userFound.setName(user.getName());
        userFound.setProfiles(user.getProfiles());

        return this.userRepository.save(userFound);
    }
}
