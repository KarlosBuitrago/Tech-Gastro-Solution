package net.carlos.dev.backend.mappers.users;

import net.carlos.dev.backend.dto.users.UsersActivityLogsDTO;
import net.carlos.dev.backend.entities.users.UsersActivityLogs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserActivityLogsMapper {
    UserActivityLogsMapper INSTANCE = Mappers.getMapper(UserActivityLogsMapper.class);

     UsersActivityLogs toEntity(UsersActivityLogsDTO userActivityLogs);
     UsersActivityLogsDTO toDTO(UsersActivityLogs userActivityLogs);
}
