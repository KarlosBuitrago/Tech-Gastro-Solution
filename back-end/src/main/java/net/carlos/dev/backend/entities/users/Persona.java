package net.carlos.dev.backend.entities.users;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_identification", nullable = false)
    private String typeIdentification;
    @Column(name = "identification", nullable = false, unique = true)
    private Long identification;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "first_last_name", nullable = false)
    private String firstLastName;
    @Column(name = "second_last_name")
    private String secondLastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "birthdate", nullable = false)
    private String birthdate;
    @Column(name = "age", nullable = false)
    private int age;
    private String role;

    @OneToOne(mappedBy = "persona")
    private User user;

}
