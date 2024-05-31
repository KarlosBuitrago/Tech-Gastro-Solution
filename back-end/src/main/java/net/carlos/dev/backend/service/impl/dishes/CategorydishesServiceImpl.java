package net.carlos.dev.backend.service.impl.dishes;

import net.carlos.dev.backend.dto.dishes.CategoryDishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.mappers.dishes.CategoryDishesMapper;
import net.carlos.dev.backend.repositories.dishes.CategoryDishesRepository;
import net.carlos.dev.backend.service.dishes.ICategoryDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ICategoryDishesService")
public class CategorydishesServiceImpl implements ICategoryDishesService {

    @Autowired
    CategoryDishesRepository categoryDishesRepository;
    CategoryDishesMapper categoryDishesMapper = CategoryDishesMapper.INSTANCE;


    @Override
    public CategoryDishesDTO createCategoryDishes(CategoryDishesDTO categoryDishesDTO) {
        CategoryDishes categoryDishes = categoryDishesMapper.toEntity(categoryDishesDTO);
        categoryDishesRepository.save(categoryDishes);
        return categoryDishesDTO;
    }

    @Override
    public boolean updateCategoryDishes(CategoryDishesDTO categoryDishesD) {
        CategoryDishes categoryDishes = categoryDishesMapper.toEntity(categoryDishesD);
        categoryDishesRepository.save(categoryDishes);
        return true;
    }

    @Override
    public boolean deleteCategoryDishes(Long id) {
        categoryDishesRepository.deleteById(id);
        return true;
    }

    @Override
    public CategoryDishes getCategoryDishes(String name) {
        return categoryDishesRepository.findByName(name);
    }

    @Override
    public CategoryDishes findById(Long id) {
        return categoryDishesRepository.findById(id).orElse(null);
    }

    @Override
    public List<CategoryDishes> getAllCategoryDishes() {
        return categoryDishesRepository.findAll();
    }
}