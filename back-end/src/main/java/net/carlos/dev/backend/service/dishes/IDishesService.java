package net.carlos.dev.backend.service.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.entities.dishes.Dishes;

import java.util.List;

public interface IDishesService {
    boolean createDishes(DishesDTO dishesDTO);
    boolean updateDishes(DishesDTO dishesDTO);
    boolean deleteDishes(Long id);
    List<Dishes> findAll();

    Dishes findById(Long id);
    List<Dishes> findByName(String name);
    List<Dishes> findByCategoryDishes(Long idCategoryDishes);
}
