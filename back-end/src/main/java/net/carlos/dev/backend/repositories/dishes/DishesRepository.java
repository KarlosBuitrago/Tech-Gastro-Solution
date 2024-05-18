package net.carlos.dev.backend.repositories.dishes;

import net.carlos.dev.backend.entities.dishes.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
    Dishes findByName(String name);
}
