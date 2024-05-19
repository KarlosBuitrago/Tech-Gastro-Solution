package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.orders.DishesOrdersDTO;

import java.util.List;

public interface IDishesOrdersService {
    boolean saveDishesOrders(DishesOrdersDTO dishesOrdersDTO);
    boolean updateDishesOrders(DishesOrdersDTO dishesOrdersDTO);
    boolean deleteDishesOrders(Long id);
    DishesOrdersDTO getDishesOrdersById(Long id);
    List<DishesOrdersDTO> getAllDishesOrders();
    DishesOrdersDTO getDishesOrdersByName(String name);
}
