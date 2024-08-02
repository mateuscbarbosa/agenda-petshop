package br.com.agenda_petshop.adapter.out.persistence.mongo;

import br.com.agenda_petshop.adapter.out.persistence.mongo.entities.UserStatusMongo;
import br.com.agenda_petshop.adapter.out.persistence.mongo.mapper.UserMongoMapper;
import br.com.agenda_petshop.adapter.out.persistence.mongo.repositories.UserMongoRepository;
import br.com.agenda_petshop.application.repositories.UserRepository;
import br.com.agenda_petshop.model.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class UserMongoDataSource implements UserRepository {
    private final UserMongoRepository mongoRepository;

    public UserMongoDataSource(UserMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public User save(User user) {
        final var userMongo = UserMongoMapper.INSTANCE.toMongo(user);
        final var savedUser = mongoRepository.save(userMongo);
        return UserMongoMapper.INSTANCE.toEntity(savedUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return mongoRepository.findByEmail(email)
                .map(UserMongoMapper.INSTANCE::toEntity);
    }

    @Override
    public User update(User user) {
        final var userMongo = UserMongoMapper.INSTANCE.toMongo(user);
        final var savedUser = mongoRepository.save(userMongo);
        return UserMongoMapper.INSTANCE.toEntity(savedUser);
    }

    @Override
    public List<User> findAllUsers() {
        return mongoRepository.findAll().stream()
                .map(UserMongoMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public void inactiveUser(User user) {
        var userMongo = UserMongoMapper.INSTANCE.toMongo(user);
        mongoRepository.save(userMongo);
    }
}
