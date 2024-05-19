package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.DishesOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesOrdersRepository extends JpaRepository<DishesOrders, Long> {
    DishesOrders findByDishes_Name(String name);}
