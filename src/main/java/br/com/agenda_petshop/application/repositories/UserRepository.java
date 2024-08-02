package br.com.agenda_petshop.application.repositories;

import br.com.agenda_petshop.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    User update(User user);
    List<User> findAllUsers();
    void inactiveUser(User user);
}
