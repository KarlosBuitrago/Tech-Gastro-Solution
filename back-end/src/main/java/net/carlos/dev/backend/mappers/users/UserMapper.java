package net.carlos.dev.backend.mappers.users;

import net.carlos.dev.backend.dto.users.UserDTO;
import net.carlos.dev.backend.entities.users.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
