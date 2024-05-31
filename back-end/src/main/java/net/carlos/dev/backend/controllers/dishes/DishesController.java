package net.carlos.dev.backend.controllers.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.entities.dishes.Dishes;
import net.carlos.dev.backend.service.impl.dishes.DishesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1/dishes")
public class DishesController {
    @Autowired
    DishesServiceImpl  dishesService;
    @Autowired
    CategoryDishesController categoryDishesController;
    @PostMapping("/dish")
    public boolean createDishes(@RequestBody DishesDTO dishesDTO){
        System.out.println(dishesDTO.getName());
        dishesService.createDishes(dishesDTO);
        return true;
    }

    @PutMapping("/dish/update")
    public boolean updateDishes(@RequestBody DishesDTO dishesDTO){
        dishesService.updateDishes(dishesDTO);
        return true;
    }

    @DeleteMapping("/dish/{id}")
    public boolean deleteDishes(@PathVariable Long id){
        dishesService.deleteDishes(id);
        return true;
    }

    @GetMapping("/dishes")
    public List<Dishes> findAll(){
        return dishesService.findAll();
    }
}
