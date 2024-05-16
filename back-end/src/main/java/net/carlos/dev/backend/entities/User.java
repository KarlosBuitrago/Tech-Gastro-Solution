package net.carlos.dev.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @OneToOne(cascade = CascadeType.ALL)
    private Persona persona;
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    @Column(name = "status")
    private String status;
    @Column(name = "apikey", nullable = false)
    private String apikey;

}
