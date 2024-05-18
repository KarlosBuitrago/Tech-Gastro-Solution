package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepository extends JpaRepository<Tables, Long> {
}
