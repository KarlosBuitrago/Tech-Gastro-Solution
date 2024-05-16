package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.dto.UserDTO;

import java.util.List;

public interface IUserService {
    public UserDTO save(PersonaDTO personaDTO);
    public UserDTO update(UserDTO user);
    public boolean delete(Long id);
    public List<UserDTO> findAll();

    public UserDTO findByIdPersona(Long idPersona);

}
