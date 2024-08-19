package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.UserNotFoundException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.UserStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class InactiveUserUseCase {
    private final UserRepository userRepository;

    public InactiveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String id){
        var userFound = userRepository.findById(id);

        if(userFound.isEmpty())
            throw new UserNotFoundException(id);

        var userToUpdate = userFound.get();

        userToUpdate.setUserStatus(UserStatus.INACTIVE);
        userToUpdate.setProfiles(new HashSet<>());

        this.userRepository.save(userToUpdate);
    }
}
