package net.carlos.dev.backend.controllers.orders;

import net.carlos.dev.backend.dto.orders.DishesOrdersDTO;
import net.carlos.dev.backend.service.impl.orders.DishesOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1/orders")
public class DishesOrdersController {
    @Autowired
    private DishesOrdersServiceImpl dishesOrdersService;

    @PostMapping("/dishes-orders")
    public boolean saveDishesOrders(@RequestBody DishesOrdersDTO dishesOrdersDTO){
        dishesOrdersService.saveDishesOrders(dishesOrdersDTO);
        return true;
    }

    @PutMapping("/dishes-orders/update")
    public boolean updateDishesOrders(@RequestBody DishesOrdersDTO dishesOrdersDTO){
        dishesOrdersService.updateDishesOrders(dishesOrdersDTO);
        return true;
    }

    @DeleteMapping("/dishes-orders/delete/{id}")
    public boolean deleteDishesOrders(@PathVariable Long id){
        dishesOrdersService.deleteDishesOrders(id);
        return true;
    }

    @GetMapping("/dishes-orders/id/{id}")
    public DishesOrdersDTO getDishesOrdersById(@PathVariable Long id){
        return dishesOrdersService.getDishesOrdersById(id);
    }

    @GetMapping("/dishes-orders-list")
    public List<DishesOrdersDTO> getAllDishesOrders(){
        return dishesOrdersService.getAllDishesOrders();
    }

    @GetMapping("/dishes-orders/name/{name}")
    public DishesOrdersDTO getDishesOrdersByName(@PathVariable String name){
        return dishesOrdersService.getDishesOrdersByName(name);
    }
//    @GetMapping("/dishes/username/{username}")
//    public List<DishesOrdersDTO> getDishesOrdersByUsername(@PathVariable String username){
//        return dishesOrdersService.getDishesOrdersByUsername(username);
//    }

}
