package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.exceptions.EntityNotFoundException;
import br.com.agenda_petshop.application.exceptions.NoUniqueValueException;
import br.com.agenda_petshop.application.exceptions.PasswordValidationException;
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
        validatePassword(user.getPassword());
        String encrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        userFound.setPassword(encrypted);

        return userRepository.save(userFound);
    }

    private void validatePassword(String password) {
        final String PASSWORD_PATTERN = "^(?=(?:.*[a-z]){2,})" +
                "(?=(?:.*[A-Z]){2,})" +
                "(?=(?:.*\\d){2,})" +
                "(?=(?:.*[!@#$%&*\\_\\+\\-]){1,})" +
                ".{8,}$";

        if (!password.matches(PASSWORD_PATTERN)) {
            throw new PasswordValidationException("A senha deve ter pelo menos 8 caracteres, " +
                    "contendo pelo menos 2 letras maiúsculas, 2 letras minúsculas, 2 números, " +
                    "e 1 dos seguintes símbolos: !@#$%&*_+-");
        }
    }
}
