package net.carlos.dev.backend.dto.users;


import lombok.Builder;
import lombok.Data;
import net.carlos.dev.backend.entities.users.Role;

@Data
@Builder(toBuilder = true)
public class PersonaUserDTO {
    //Datos de persona
    private Long id;
    private String typeIdentification;
    private Long identification;
    private String firstName;
    private String middleName;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String birthdate;
    private int age;

    //Datos de usuario
    private String username;
    private String role;
    private String status;

}
