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
    private String role;

    public int calculateAge() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dateBirthDate = LocalDate.parse(this.birthdate, fmt);
        LocalDate actualDate = LocalDate.now();
        int theAge = actualDate.getYear() - dateBirthDate.getYear();
        if (actualDate.getMonthValue() < dateBirthDate.getMonthValue() ||
                (actualDate.getMonthValue() == dateBirthDate.getMonthValue() && actualDate
                        .getDayOfMonth() < dateBirthDate.getDayOfMonth())) {
            theAge--;
        }
        return theAge;
    }
}
