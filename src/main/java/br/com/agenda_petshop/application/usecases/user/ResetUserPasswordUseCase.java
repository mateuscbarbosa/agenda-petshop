package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.UserNotFoundException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetUserPasswordUseCase {
    private final UserRepository userRepository;
    private final NewPasswordUserEmailSenderUseCase newPasswordUserEmailSenderUseCase;

    public ResetUserPasswordUseCase(UserRepository userRepository, NewPasswordUserEmailSenderUseCase newPasswordUserEmailSenderUseCase) {
        this.userRepository = userRepository;
        this.newPasswordUserEmailSenderUseCase = newPasswordUserEmailSenderUseCase;
    }

    public User execute(String id) {
        final var userById = userRepository.findById(id);

        if(userById.isEmpty())
            throw new UserNotFoundException(id);

        var userFound = userById.get();

        userFound.generatePassword(10);
        String encrypted = new BCryptPasswordEncoder().encode(userFound.getPassword());

        newPasswordUserEmailSenderUseCase.execute(userFound,
                "Reset de senha usuário "+userFound.getName()+" Agenda PetShop");

        userFound.setPassword(encrypted);
        userFound.setFirstPassword(true);

        return this.userRepository.save(userFound);
    }
}
