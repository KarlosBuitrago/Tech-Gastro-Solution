package net.carlos.dev.backend.dto;

import lombok.*;
import net.carlos.dev.backend.entities.Persona;
import net.carlos.dev.backend.entities.Role;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {
    private Persona persona;
    private String username;
    private String password;
    private Role role;
    private String status;
    private String apikey;
}
