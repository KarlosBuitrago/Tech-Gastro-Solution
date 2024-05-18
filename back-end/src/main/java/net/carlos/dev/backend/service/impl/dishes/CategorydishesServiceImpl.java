package net.carlos.dev.backend.service.impl.dishes;

import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.repositories.dishes.CategoryDishesRepository;
import net.carlos.dev.backend.service.dishes.ICategoryDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ICategoryDishesService")
public class CategorydishesServiceImpl implements ICategoryDishesService {

    @Autowired
    CategoryDishesRepository categoryDishesRepository;


    @Override
    public CategoryDishes createCategoryDishes(String categoryName) {
        CategoryDishes categoryDishes = new CategoryDishes();
        categoryDishes.setName(categoryName);
        return categoryDishesRepository.save(categoryDishes);
    }

    @Override
    public boolean updateCategoryDishes(CategoryDishes categoryDishesDTO) {
        return false;
    }

    @Override
    public boolean deleteCategoryDishes(Long id) {
        return false;
    }

    @Override
    public CategoryDishes getCategoryDishes(String name) {
        return null;
    }

    @Override
    public CategoryDishes findById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDishes> getAllCategoryDishes() {
        return List.of();
    }
}