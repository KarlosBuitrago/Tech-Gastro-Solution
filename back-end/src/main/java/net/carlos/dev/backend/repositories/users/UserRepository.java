package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, PersonaDTO> {
    public abstract User findByUsername(String username);
}
