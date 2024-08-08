package br.com.agenda_petshop.application.repositories;

import br.com.agenda_petshop.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    List<User> findAllUsers();
}
