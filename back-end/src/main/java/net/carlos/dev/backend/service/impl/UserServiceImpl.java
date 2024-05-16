package net.carlos.dev.backend.service.impl;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.dto.UserDTO;
import net.carlos.dev.backend.entities.Persona;
import net.carlos.dev.backend.entities.Role;
import net.carlos.dev.backend.entities.User;
import net.carlos.dev.backend.mappers.PersonaMapper;
import net.carlos.dev.backend.mappers.UserMapper;
import net.carlos.dev.backend.repositories.PersonaRepository;
import net.carlos.dev.backend.repositories.UserRepository;
import net.carlos.dev.backend.service.IUserService;
import net.carlos.dev.backend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IUserService")
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PersonaRepository personaRepository;
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PersonaRepository personaRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.personaRepository = personaRepository;
    }

    @Override
    public UserDTO save(PersonaDTO personaDTO) {
        if (personaDTO == null) {
            throw new IllegalArgumentException("personaDTO no puede ser nulo");
        }
        UserDTO userDTO = new UserDTO();
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            return null;
        }else {

            Role role = roleService.createRole(personaDTO.getRole());
            userDTO.setRole(role);
            userDTO.createUsername(personaDTO);
            userDTO.createPassword(personaDTO);
            userDTO.createApiKey();

            User userEntity = userMapper.toEntity(userDTO);

            if (personaDTO.getId() != null) { // Asegúrate de que el ID de Persona no sea nulo
                Optional<Persona> existingPersona = personaRepository.findById(personaDTO.getId());
                if (existingPersona.isPresent()) {
                    userEntity.setPersona(existingPersona.get());
                }else {
                    throw new IllegalArgumentException("No se encontró la persona con el ID: " + personaDTO.getId());
                }
            }
            userEntity = userRepository.save(userEntity);
            return userMapper.toDTO(userEntity);
        }


    }

    @Override
    public UserDTO update(UserDTO user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public UserDTO findByIdPersona(Long idPersona) {
        return null;
    }

    public UserDTO convertToDTO(User user){
        return userMapper.toDTO(user);
    }

    public User convertToEntity(UserDTO userDTO){
        return userMapper.toEntity(userDTO);
    }
}
