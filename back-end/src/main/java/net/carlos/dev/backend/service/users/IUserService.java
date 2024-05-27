package net.carlos.dev.backend.service.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.dto.users.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    public UserDTO save(PersonaDTO personaDTO);
    public UserDTO update(UserDTO user);
    public boolean delete(Long id);
    public List<UserDTO> findAll();
    public UserDTO findByIdPersona(Long idPersona);
    UserDetails loadUserByUsername(String username, String password);
}
