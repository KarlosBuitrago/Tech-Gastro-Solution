package net.carlos.dev.backend.service.impl.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.dto.dishes.PhotoDishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.entities.dishes.Dishes;
import net.carlos.dev.backend.entities.dishes.PhotoDishes;
import net.carlos.dev.backend.mappers.dishes.CategoryDishesMapper;
import net.carlos.dev.backend.mappers.dishes.DishesMapper;
import net.carlos.dev.backend.mappers.dishes.PhotoDishesMapper;
import net.carlos.dev.backend.repositories.dishes.CategoryDishesRepository;
import net.carlos.dev.backend.repositories.dishes.DishesRepository;
import net.carlos.dev.backend.service.dishes.IDishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("IDishesService")
public class DishesServiceImpl implements IDishesService {
    @Autowired
    DishesRepository dishesRepository;

    @Autowired
    CategorydishesServiceImpl categoryDishes;

    @Autowired
    CategoryDishesRepository categoryDishesRepository;

    DishesMapper dishesMapper = DishesMapper.INSTANCE;
    CategoryDishesMapper categoryDishesMapper = CategoryDishesMapper.INSTANCE;
    PhotoDishesMapper photoDishesMapper = PhotoDishesMapper.INSTANCE;


    @Override
    public boolean createDishes(DishesDTO dishesDTO) {

        Dishes dishes = dishesMapper.toEntity(dishesDTO);
        CategoryDishes categoryDishes1 = categoryDishesMapper.toEntity(dishesDTO.getCategoryDishesDTO());
        categoryDishesRepository.save(categoryDishes1);

        List<PhotoDishes> photoDishes = dishesDTO.getPhotoDishesDTO().stream()
                .map(photoDishesDTO1 -> photoDishesMapper.toEntity(photoDishesDTO1))
                .peek(photoDishes1 -> photoDishes1.setDishes(dishes))
                .collect(Collectors.toList());

        dishes.setCategoryDishes(categoryDishes1);
        dishes.setPhotoDishes(photoDishes);

        dishesRepository.save(dishes);
        return true;


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
