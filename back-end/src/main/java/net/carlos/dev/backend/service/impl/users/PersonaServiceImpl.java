package net.carlos.dev.backend.service.impl.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.entities.users.Persona;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.repositories.users.PersonaRepository;
import net.carlos.dev.backend.service.users.IPersonaServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("IPersonaServices")
public class PersonaServiceImpl implements IPersonaServices {

    private static final Logger LOGGER = LogManager.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }


    @Override
    public PersonaDTO save(PersonaDTO personaDTO) {
        try {

            if (personaDTO.getIdentification() == null) {
                LOGGER.error("El campo identification es obligatorio");
                return null;
            }
            if (personaDTO.getIdentification() == 0){
                LOGGER.error("No se pudo guardar la persona");
                return null;
            } else {
                personaDTO.setAge(personaDTO.calculateAge());
                Persona personaEntity = personaMapper.toEntity(personaDTO);
                Persona personaSaved = personaRepository.save(personaEntity);
                return personaMapper.toDTO(personaSaved);
            }
        }catch (Exception e){
            LOGGER.error("Error al guardar la persona", e);
            return null;
        }
    }

    @Override
    public PersonaDTO update(PersonaDTO personaDTO) {
        try {
            if (personaDTO == null || personaDTO.getId() == null) {
                LOGGER.error("Persona no puede ser null");
                return null;
            }else {
                Persona personaEntity = personaMapper.toEntity(personaDTO);
                Persona personaSaved = personaRepository.save(personaEntity);
                return personaMapper.toDTO(personaSaved);
            }
        }catch (Exception e){
            LOGGER.error("Error al actualizar la persona ", e);
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<PersonaDTO> findAll() {
        return List.of();
    }

    @Override
    public PersonaDTO findById(Long id) {
        return null;
    }

    @Override
    public PersonaDTO findByEmail(String email) {
        return null;
    }

    @Override
    public PersonaDTO findByIdentification(Long identification) {
        return null;
    }

    @Override
    public List<PersonaDTO> findByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public List<PersonaDTO> findByFirstLastName(String firstLastName) {
        return List.of();
    }

    public PersonaDTO convertToDTO(Persona persona){
        return personaMapper.toDTO(persona);
    }

    public Persona convertToEntity(PersonaDTO personaDTO){
        return personaMapper.toEntity(personaDTO);
    }

}
