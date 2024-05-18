package net.carlos.dev.backend.repositories.dishes;

import net.carlos.dev.backend.entities.dishes.PhotoDishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDishesRepository extends JpaRepository<PhotoDishes, Long> {
}
