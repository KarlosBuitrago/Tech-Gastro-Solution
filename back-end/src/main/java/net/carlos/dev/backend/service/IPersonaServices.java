package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.PersonaDTO;

import java.util.List;

public interface IPersonaServices {

    public PersonaDTO save(PersonaDTO persona);
    public PersonaDTO update(PersonaDTO persona);
    public boolean delete(Long id);
    public List<PersonaDTO> findAll();

    public PersonaDTO findById(Long id);
    public PersonaDTO findByEmail(String email);
    public PersonaDTO findByIdentification(Long identification);
    public List<PersonaDTO> findByFirstName(String firstName);
    public List<PersonaDTO> findByFirstLastName(String firstLastName);

}
