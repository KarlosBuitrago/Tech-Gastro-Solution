package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.dto.PersonaDTO;
import net.carlos.dev.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, PersonaDTO> {
    public abstract User findByUsername(String username);
}
