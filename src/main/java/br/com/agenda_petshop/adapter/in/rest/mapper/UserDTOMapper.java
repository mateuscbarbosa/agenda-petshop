package br.com.agenda_petshop.adapter.in.rest.mapper;

import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserCreationRequest;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserResponse;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserSelfUpdateRequest;
import br.com.agenda_petshop.adapter.in.rest.dtos.user.UserUpdateRequest;
import br.com.agenda_petshop.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDTOMapper {
    UserDTOMapper INSTANCE = Mappers.getMapper(UserDTOMapper.class);

    UserResponse mapToResponse(User source);

    User mapToEntity(UserCreationRequest source);

    User mapToEntity(UserUpdateRequest request);

    User mapToEntity(UserSelfUpdateRequest request);
}
