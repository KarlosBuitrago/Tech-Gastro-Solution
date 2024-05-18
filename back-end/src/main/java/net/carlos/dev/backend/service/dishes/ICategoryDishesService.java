package net.carlos.dev.backend.service.dishes;


import net.carlos.dev.backend.entities.dishes.CategoryDishes;

import java.util.List;

public interface ICategoryDishesService {
    CategoryDishes createCategoryDishes(String categoryDishesName);
    boolean updateCategoryDishes(CategoryDishes categoryDishes);
    boolean deleteCategoryDishes(Long id);
    CategoryDishes getCategoryDishes(String name);
    CategoryDishes findById(Long id);
    List<CategoryDishes> getAllCategoryDishes();

}
