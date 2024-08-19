package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.NoUniqueEmailException;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import br.com.agenda_petshop.model.user.UserStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final NewPasswordUserEmailSenderUseCase newPasswordUserEmailSenderUseCase;

    public CreateUserUseCase(UserRepository userRepository, NewPasswordUserEmailSenderUseCase newPasswordUserEmailSenderUseCase) {
        this.userRepository = userRepository;
        this.newPasswordUserEmailSenderUseCase = newPasswordUserEmailSenderUseCase;
    }

    public User execute(User user){
        final var userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByEmail.isPresent())
            throw new NoUniqueEmailException();

        user.generatePassword(10);
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());

        newPasswordUserEmailSenderUseCase.execute(user, "Bem vindo ao Agenda PetShop");

        user.setPassword(encrypted);
        user.setFirstPassword(true);
        user.setUserStatus(UserStatus.ACTIVE);

        return this.userRepository.save(user);
    }
}
