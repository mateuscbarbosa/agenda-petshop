package br.com.agenda_petshop.adapter.out.persistence.mongo.mapper;

import br.com.agenda_petshop.adapter.out.persistence.mongo.entities.UserMongo;
import br.com.agenda_petshop.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMongoMapper {
    UserMongoMapper INSTANCE = Mappers.getMapper(UserMongoMapper.class);

    UserMongo toMongo(User user);
    User toEntity(UserMongo userMongo);
}
