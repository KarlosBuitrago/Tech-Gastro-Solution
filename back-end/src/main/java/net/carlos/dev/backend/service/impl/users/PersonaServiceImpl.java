package net.carlos.dev.backend.service.impl.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.dto.users.PersonaUserDTO;
import net.carlos.dev.backend.entities.users.Persona;
import net.carlos.dev.backend.entities.users.User;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.mappers.users.PersonaUserMapper;
import net.carlos.dev.backend.repositories.users.PersonaRepository;
import net.carlos.dev.backend.service.users.IPersonaServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("IPersonaServices")
public class PersonaServiceImpl implements IPersonaServices {

    private static final Logger LOGGER = LogManager.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;

    private final PersonaUserMapper personaUserMapper = PersonaUserMapper.INSTANCE;
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
        if (personaRepository.findById(id).isPresent()){
            personaRepository.deleteById(id);
            return true;
        }else {
            LOGGER.error("No se pudo eliminar la persona");
            return false;
        }

    }

    @Override
    public List<PersonaDTO> findAll() {
        return List.of(personaMapper.toDTO((Persona) personaRepository.findAll()));
    }

    @Override
    public PersonaDTO findById(Long id) {
        if (personaRepository.findById(id).isPresent()){
            return personaMapper.toDTO(personaRepository.findById(id).get());
        }else{
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }

    }

    @Override
    public PersonaDTO findByEmail(String email) {
        if (personaRepository.findByEmail(email) != null) {
            return personaMapper.toDTO(personaRepository.findByEmail(email));
        }else {
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }
    }

    @Override
    public PersonaDTO findByIdentification(Long identification) {
        if (personaRepository.findByIdentification(identification) != null) {
            return personaMapper.toDTO(personaRepository.findByIdentification(identification));
        }else {
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }
    }

    @Override
    public List<PersonaDTO> findByFirstName(String firstName) {
        if (personaRepository.findByFirstName(firstName) != null) {
            return Collections.singletonList(personaMapper.toDTO(personaRepository.findByFirstName(firstName)));
        } else {
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }

    }

    @Override
    public List<PersonaDTO> findByFirstLastName(String firstLastName) {
        if (personaRepository.findByFirstLastName(firstLastName) != null) {
            return Collections.singletonList(personaMapper.toDTO(personaRepository.findByFirstLastName(firstLastName)));
        } else {
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }
    }

    public List<PersonaDTO> findByAge(Integer age) {
        if (personaRepository.findByAge(age) != null) {
            return Collections.singletonList(personaMapper.toDTO(personaRepository.findByAge(age)));
        } else {
            LOGGER.error("No se pudo encontrar la persona");
            return null;
        }
    }

    public List<PersonaUserDTO> findAllPersonasUsers() {
        List<Object[]> personasUsers = personaRepository.findAllWithUser();
        return personasUsers.stream()
                .map(personaUser -> {
            Persona persona = (Persona) personaUser[0];
            User user = (User) personaUser[1];
            return personaUserMapper.toDTO(persona, user);

        })
                .collect(Collectors.toList());
    }

    public List<PersonaUserDTO> findAllPersonasUsersActive() {
        List<Object[]> personasUsers = personaRepository.findAllWithActiveUser();
        return personasUsers.stream()
                .map(personaUser -> {
                    Persona persona = (Persona) personaUser[0];
                    User user = (User) personaUser[1];
                    return personaUserMapper.toDTO(persona, user);

                })
                .collect(Collectors.toList());
    }
}
