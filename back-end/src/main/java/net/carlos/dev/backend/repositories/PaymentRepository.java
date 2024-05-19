package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByOrders_OrderNumber(String orderNumber);
}
