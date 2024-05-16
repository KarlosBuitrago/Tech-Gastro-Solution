package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
