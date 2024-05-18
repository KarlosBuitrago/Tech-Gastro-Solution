package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
    PayMethod findByName(String name);
}
