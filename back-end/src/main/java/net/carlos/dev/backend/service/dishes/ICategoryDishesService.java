package net.carlos.dev.backend.service.dishes;


import net.carlos.dev.backend.dto.dishes.CategoryDishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;

import java.util.List;

public interface ICategoryDishesService {
    boolean updateCategoryDishes(CategoryDishesDTO categoryDishes);
    boolean deleteCategoryDishes(Long id);
    CategoryDishes getCategoryDishes(String name);
    CategoryDishes findById(Long id);
    List<CategoryDishes> getAllCategoryDishes();

}
