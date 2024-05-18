package net.carlos.dev.backend.service.impl.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.entities.dishes.Dishes;
import net.carlos.dev.backend.mappers.dishes.DishesMapper;
import net.carlos.dev.backend.repositories.dishes.CategoryDishesRepository;
import net.carlos.dev.backend.repositories.dishes.DishesRepository;
import net.carlos.dev.backend.service.dishes.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IDishesService")
public class DishesServiceImpl implements IDishesService {
    @Autowired
    DishesRepository dishesRepository;

    @Autowired
    CategorydishesServiceImpl categoryDishes;
    DishesMapper dishesMapper = DishesMapper.INSTANCE;

    @Override
    public boolean createDishes(DishesDTO dishesDTO) {

            if (dishesDTO == null) {
                throw new IllegalArgumentException("dishesDTO no puede ser nulo");
            }
            Dishes dishes = dishesMapper.toEntity(dishesDTO);

            if (dishesRepository.findByName(dishesDTO.getName()) != null) {
                return false;
            } else {
                String nameCategory = null;
                if (dishesDTO.getCategoryDishes() != null) {
                    nameCategory = dishesDTO.getCategoryDishes().getName();
                }
                CategoryDishes categoryDishes1 = null;
                if (nameCategory != null) {
                    categoryDishes1 = categoryDishes.createCategoryDishes(nameCategory);
                }

                dishes.setCategoryDishes(categoryDishes1);
                dishesRepository.save(dishes);
                return true;
            }
        }

    

    @Override
    public boolean updateDishes(DishesDTO dishesDTO) {
        if (dishesDTO == null) {
            throw new IllegalArgumentException("dishesDTO no puede ser nulo");
        }
        if (dishesRepository.findByName(dishesDTO.getName()) != null) {
            return false;
        }else {
            Dishes dishes = dishesMapper.toEntity(dishesDTO);
            dishesRepository.save(dishes);
            return true;
        }
    }

    @Override
    public boolean deleteDishes(Long id) {
        if (dishesRepository.findById(id).isEmpty()) {
            return false;
        }else {
            dishesRepository.deleteById(id);
            return true;
        }

    }

    @Override
    public List<Dishes> findAll() {
        return dishesRepository.findAll();
    }

    @Override
    public Dishes findById(Long id) {
        return null;
    }

    @Override
    public List<Dishes> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Dishes> findByCategoryDishes(Long idCategoryDishes) {
        return List.of();
    }
}
