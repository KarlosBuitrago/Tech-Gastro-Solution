package net.carlos.dev.backend.controllers.dishes;

import net.carlos.dev.backend.dto.dishes.CategoryDishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import net.carlos.dev.backend.service.impl.dishes.CategorydishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1/dishes")
public class CategoryDishesController {
    @Autowired
    CategorydishesServiceImpl categorydishesService;

    @PostMapping("/category")
    public CategoryDishesDTO createCategoryDishes(@RequestBody CategoryDishesDTO categoryName){
        categorydishesService.updateCategoryDishes(categoryName);
        return categoryName;
    }


    @PutMapping("/category-dishes")
    public boolean updateCategoryDishes(@RequestBody CategoryDishesDTO categoryName){
        categorydishesService.updateCategoryDishes(categoryName);
        return true;
    }

    @GetMapping("/category-dishes/all")
    public List<CategoryDishes> findAll(){
        return categorydishesService.getAllCategoryDishes();
    }
    @DeleteMapping("/category-dishes/{id}")
    public boolean deleteCategoryDishes(@PathVariable Long id){
        categorydishesService.deleteCategoryDishes(id);
        return true;
    }

    @GetMapping("/category-dishes/id/{id}")
    public CategoryDishes findById(@PathVariable Long id){
        return categorydishesService.findById(id);
    }
}
