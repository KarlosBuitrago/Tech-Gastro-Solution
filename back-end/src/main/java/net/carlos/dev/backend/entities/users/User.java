package net.carlos.dev.backend.entities.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.carlos.dev.backend.dto.users.UserDTO;
import net.carlos.dev.backend.entities.Orders;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
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
    @OneToMany(mappedBy = "user")
    private List<UsersActivityLogs> usersActivityLogs;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

}
