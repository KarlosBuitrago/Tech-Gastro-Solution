package net.carlos.dev.backend.controllers.orders;

import net.carlos.dev.backend.dto.orders.DishesOrdersDTO;
import net.carlos.dev.backend.service.impl.DishesOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1")
public class DishesOrdersController {
    @Autowired
    private DishesOrdersServiceImpl dishesOrdersService;

    @PostMapping("/dishes-orders")
    public boolean saveDishesOrders(@RequestBody DishesOrdersDTO dishesOrdersDTO){
        dishesOrdersService.saveDishesOrders(dishesOrdersDTO);
        return true;
    }

    @PutMapping("/dishes-orders")
    public boolean updateDishesOrders(@RequestBody DishesOrdersDTO dishesOrdersDTO){
        dishesOrdersService.updateDishesOrders(dishesOrdersDTO);
        return true;
    }

    @DeleteMapping("/dishes-orders/{id}")
    public boolean deleteDishesOrders(@PathVariable Long id){
        dishesOrdersService.deleteDishesOrders(id);
        return true;
    }

    @GetMapping("/dishes-orders/{id}")
    public DishesOrdersDTO getDishesOrdersById(@PathVariable Long id){
        return dishesOrdersService.getDishesOrdersById(id);
    }

    @GetMapping("/dishes-orders-list")
    public List<DishesOrdersDTO> getAllDishesOrders(){
        return dishesOrdersService.getAllDishesOrders();
    }

    @GetMapping("/dishes-orders/{name}")
    public DishesOrdersDTO getDishesOrdersByName(@PathVariable String name){
        return dishesOrdersService.getDishesOrdersByName(name);
    }

}
