package net.carlos.dev.backend.dto.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsersActivityLogsDTO {
    private Long id;
    private String activity;
    private String activityDateTime;
    private UserDTO userDTO;
}
