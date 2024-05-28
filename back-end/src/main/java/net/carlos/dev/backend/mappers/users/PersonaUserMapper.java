package net.carlos.dev.backend.mappers.users;

import net.carlos.dev.backend.dto.users.PersonaUserDTO;
import net.carlos.dev.backend.entities.users.Persona;
import net.carlos.dev.backend.entities.users.User;

public class PersonaUserMapper {
    public static PersonaUserMapper INSTANCE = new PersonaUserMapper();
    public PersonaUserDTO toDTO(Persona persona, User user){
        PersonaUserDTO personaUser = PersonaUserDTO.builder()
                .id(persona.getId())
                .typeIdentification(persona.getTypeIdentification())
                .identification(persona.getIdentification())
                .firstName(persona.getFirstName())
                .middleName(persona.getMiddleName())
                .firstLastName(persona.getFirstLastName())
                .secondLastName(persona.getSecondLastName())
                .email(persona.getEmail())
                .phone(persona.getPhone())
                .address(persona.getAddress())
                .city(persona.getCity())
                .country(persona.getCountry())
                .birthdate(persona.getBirthdate())
                .age(persona.getAge())
                .role(user.getRole().getRoleName())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
        return personaUser;
    }
}
