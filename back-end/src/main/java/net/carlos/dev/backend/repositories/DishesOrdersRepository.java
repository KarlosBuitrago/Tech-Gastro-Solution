package net.carlos.dev.backend.repositories;

import net.carlos.dev.backend.entities.DishesOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DishesOrdersRepository extends JpaRepository<DishesOrders, Long> {
    DishesOrders findByDishes_Name(String name);

//    List<DishesOrders> findByDishesUsername(String username);
}
