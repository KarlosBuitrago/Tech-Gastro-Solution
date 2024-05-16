package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.UserDTO;
import net.carlos.dev.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
