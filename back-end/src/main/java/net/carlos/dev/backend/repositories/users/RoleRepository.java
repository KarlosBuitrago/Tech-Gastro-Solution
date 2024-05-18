package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.entities.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
