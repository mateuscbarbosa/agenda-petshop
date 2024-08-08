package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.EntityNotFoundException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangeFirstPasswordUserUseCase {
    private final UserRepository userRepository;

    public ChangeFirstPasswordUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user){
        var userFound = userRepository.findByEmail(user.getEmail());

        if(userFound.isEmpty())
            throw new EntityNotFoundException("Usuário não encontrado.");

        var userToUpdate = userFound.get();

        if (!userToUpdate.isFirstPassword())
            throw new IllegalArgumentException("Não é a primeira senha.");
        //TODO: precisa criar uma validação de senha com as regras
        userToUpdate.setFirstPassword(false);
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encrypted);

        return this.userRepository.save(user);
    }
}
