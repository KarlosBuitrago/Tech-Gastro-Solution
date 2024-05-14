package net.carlos.dev.backend.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class PersonaDTO {

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


}
