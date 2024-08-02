package br.com.agenda_petshop.adapter.out.persistence.mongo.repositories;

import br.com.agenda_petshop.adapter.out.persistence.mongo.entities.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserMongo, String> {
    Optional<UserMongo> findByEmail(String email);
}
