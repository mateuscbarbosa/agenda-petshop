package br.com.agenda_petshop.application.usecases.user;

import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersUseCase {
    private final UserRepository userRepository;

    public ListUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute(){
        return this.userRepository.findAllUsers();
    }
}
