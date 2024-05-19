package net.carlos.dev.backend.service.users;

import net.carlos.dev.backend.dto.users.UserDTO;

public interface IUserActivityLogsService {
    void saveUserActivityLogs(UserDTO userDTO,String activity);
}
