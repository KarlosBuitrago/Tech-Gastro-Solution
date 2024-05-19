package net.carlos.dev.backend.service.impl.users;

import net.carlos.dev.backend.dto.users.UserDTO;
import net.carlos.dev.backend.dto.users.UsersActivityLogsDTO;
import net.carlos.dev.backend.entities.users.UsersActivityLogs;
import net.carlos.dev.backend.mappers.users.UserActivityLogsMapper;
import net.carlos.dev.backend.repositories.users.UserActivityLogsRepository;
import net.carlos.dev.backend.service.users.IUserActivityLogsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("IUserActivityLogsService")
public class UserActivityLogsServiceImpl implements IUserActivityLogsService {
    private final UserActivityLogsRepository userActivityLogsRepository;
    private UserActivityLogsMapper userActivityLogsMapper = UserActivityLogsMapper.INSTANCE;
    public UserActivityLogsServiceImpl(UserActivityLogsRepository userActivityLogsRepository) {
        this.userActivityLogsRepository = userActivityLogsRepository;
    }

    @Override
    public void saveUserActivityLogs(UserDTO userDTO, String activity) {
        UsersActivityLogsDTO usersActivityLogsDTO = new UsersActivityLogsDTO();
        usersActivityLogsDTO.setActivity(activity);
        usersActivityLogsDTO.setUserDTO(userDTO);
        usersActivityLogsDTO.setActivityDateTime(LocalDateTime.now().toString());
        UsersActivityLogs usersActivityLogs = userActivityLogsMapper.toEntity(usersActivityLogsDTO);

        userActivityLogsRepository.save(usersActivityLogs);
    }
}
