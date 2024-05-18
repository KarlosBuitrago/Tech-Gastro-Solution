package net.carlos.dev.backend.service.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;

import java.util.List;

public interface IPersonaServices {

    PersonaDTO save(PersonaDTO persona);
    PersonaDTO update(PersonaDTO persona);
    boolean delete(Long id);
    List<PersonaDTO> findAll();

    PersonaDTO findById(Long id);
    PersonaDTO findByEmail(String email);
    PersonaDTO findByIdentification(Long identification);
    List<PersonaDTO> findByFirstName(String firstName);
    List<PersonaDTO> findByFirstLastName(String firstLastName);

}
