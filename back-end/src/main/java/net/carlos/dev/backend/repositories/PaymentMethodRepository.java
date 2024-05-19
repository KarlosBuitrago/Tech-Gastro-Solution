package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    PaymentMethod findByName(String name);
}
