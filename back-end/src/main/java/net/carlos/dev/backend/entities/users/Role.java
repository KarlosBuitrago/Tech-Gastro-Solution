package net.carlos.dev.backend.entities.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name", nullable = false)
    private String roleName;
    @OneToOne(mappedBy = "role")
    private User users;

}
