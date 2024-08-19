package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.UserNotFoundException;
import br.com.agenda_petshop.application.exceptions.PasswordValidationException;
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
            throw new UserNotFoundException(user.getEmail());

        var userToUpdate = userFound.get();

        if (!userToUpdate.isFirstPassword())
            throw new IllegalArgumentException("Não é a primeira senha.");

        userToUpdate.setFirstPassword(false);
        validatePassword(user.getPassword());
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        userToUpdate.setPassword(encrypted);

        return this.userRepository.save(userToUpdate);
    }

    private void validatePassword(String password) {
        final String PASSWORD_PATTERN = "^(?=(?:.*[a-z]){2,})" +
                "(?=(?:.*[A-Z]){2,})" +
                "(?=(?:.*\\d){2,})" +
                "(?=(?:.*[!@#$%&*\\_\\+\\-]){1,})" +
                ".{8,}$";

        if (!password.matches(PASSWORD_PATTERN)) {
            throw new PasswordValidationException();
        }
    }
}
