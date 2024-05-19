package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByOrderNumber(String orderNumber);

    List<Orders> findByUserUsername(String username);

    List<Orders> findByStatus(String status);
}
