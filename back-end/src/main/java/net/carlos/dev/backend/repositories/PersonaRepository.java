package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
