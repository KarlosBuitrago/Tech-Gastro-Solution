package net.carlos.dev.backend.controllers.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.service.impl.dishes.CategorydishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gastro-tech/api/v1")
public class CategoryDishesController {
    @Autowired
    CategorydishesServiceImpl categorydishesService;

    public boolean createCategoryDishes(DishesDTO dishesDTO){
        String categoryName = dishesDTO.getCategoryDishes().getName();
        categorydishesService.createCategoryDishes(categoryName);
        return true;
    }
}
