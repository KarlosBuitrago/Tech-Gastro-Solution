package net.carlos.dev.backend.service.impl.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.dto.users.UserDTO;
import net.carlos.dev.backend.entities.users.Persona;
import net.carlos.dev.backend.entities.users.Role;
import net.carlos.dev.backend.entities.users.User;
import net.carlos.dev.backend.mappers.users.PersonaMapper;
import net.carlos.dev.backend.mappers.users.UserMapper;
import net.carlos.dev.backend.repositories.users.PersonaRepository;
import net.carlos.dev.backend.repositories.users.UserRepository;
import net.carlos.dev.backend.service.users.IUserService;
import net.carlos.dev.backend.service.users.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("IUserService")
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;
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
        Persona personaEntity = personaMapper.toEntity(personaDTO);
        personaEntity = personaRepository.save(personaEntity);

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
            userEntity.setPersona(personaEntity);

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

    public boolean activateUser(Long id, String status){
        User user = userRepository.findByPersonaId(id);
        user.setStatus(status);
        user = userRepository.save(user);
        return true;
    }

    public UserDetails loadUserByUsername(String username, String password){

        User appUser = userRepository.findByUsernamePassword(username, password);

        List<GrantedAuthority> granList = new ArrayList<>();

        UserDetails user = new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), granList);
        return user;
    }
}
