package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.dto.users.PersonaDTO;
import net.carlos.dev.backend.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, PersonaDTO> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public abstract User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    public abstract User findByUsernamePassword(String username, String password);
    public abstract User findByPersonaId(Long id);

    int deleteByPersonaId(Long id);
}
