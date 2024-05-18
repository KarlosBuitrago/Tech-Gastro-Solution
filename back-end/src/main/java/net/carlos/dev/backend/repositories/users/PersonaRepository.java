package net.carlos.dev.backend.repositories.users;

import net.carlos.dev.backend.entities.users.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
