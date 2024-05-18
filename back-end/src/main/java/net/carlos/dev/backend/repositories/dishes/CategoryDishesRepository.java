package net.carlos.dev.backend.repositories.dishes;

import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDishesRepository extends JpaRepository<CategoryDishes, Long> {
    CategoryDishes findByName(String name);
}
