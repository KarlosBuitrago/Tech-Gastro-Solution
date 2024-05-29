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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("IUserService")
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PersonaMapper personaMapper = PersonaMapper.INSTANCE;
    private final PersonaRepository personaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
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
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
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
        return  userRepository.deleteByPersonaId(id) > 0;

    }

    @Override
    public List<UserDTO> findAll() {
        return List.of();
    }

    @Override
    public UserDTO findByIdPersona(Long idPersona) {
        User user = userRepository.findByPersonaId(idPersona);
        return userMapper.toDTO(user);
    }

    public boolean activateUser(Long id, String status){
        System.out.println(status);
        User user = userRepository.findByPersonaId(id);
        if (user.getStatus() == null){
            return false;
        }else {
            user.setStatus(status);
            System.out.println(user.getStatus());
            userRepository.save(user);
            return true;
        }

    }

    public UserDetails loadUserByUsername(String username, String password){
        System.out.println(username +"     "+ password);
        User appUser = userRepository.findByUsername(username);
        System.out.println(appUser.getUsername());
        List<GrantedAuthority> granList = new ArrayList<>();
        UserDetails user = null;
        if (appUser.getStatus().equalsIgnoreCase("Activo") && bCryptPasswordEncoder.matches(password, appUser.getPassword())){
            user = new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), granList);
        }else {
            LOGGER.error("Usuario no activo");
            return null;
        }
        return user;
    }

    @Override
    public void changePassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }else {
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
        }

    }
}
