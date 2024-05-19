package net.carlos.dev.backend.service.impl;

import net.carlos.dev.backend.dto.orders.DishesOrdersDTO;
import net.carlos.dev.backend.entities.DishesOrders;
import net.carlos.dev.backend.mappers.DishesOrdersMapper;
import net.carlos.dev.backend.repositories.DishesOrdersRepository;
import net.carlos.dev.backend.repositories.OrdersRepository;
import net.carlos.dev.backend.repositories.dishes.DishesRepository;
import net.carlos.dev.backend.service.IDishesOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IDishesOrdersService")
public class DishesOrdersServiceImpl implements IDishesOrdersService {
    @Autowired
    private DishesOrdersRepository dishesOrdersRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DishesRepository dishesRepository;

    private final DishesOrdersMapper dishesOrdersMapper = DishesOrdersMapper.INSTANCE;
    @Override
    public boolean saveDishesOrders(DishesOrdersDTO dishesOrdersDTO) {
        dishesOrdersDTO.calculateDateTimeOrder();
        DishesOrders dishesOrders = dishesOrdersMapper.toEntity(dishesOrdersDTO);
        dishesOrders.setDishes(dishesRepository.getReferenceById(dishesOrdersDTO.getDishesDTO().getId()));
        dishesOrders.setOrders(ordersRepository.getReferenceById(dishesOrdersDTO.getOrdersDTO().getId()));

        dishesOrdersRepository.save(dishesOrders);
        return false;
    }

    @Override
    public boolean updateDishesOrders(DishesOrdersDTO dishesOrdersDTO) {
        if (dishesOrdersDTO != null){
            DishesOrders dishesOrders = dishesOrdersMapper.toEntity(dishesOrdersDTO);
            if (dishesOrdersRepository.findById(dishesOrders.getId()).isPresent()){
                return false;
            }else {
                dishesOrdersRepository.save(dishesOrders);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteDishesOrders(Long id) {
        if (dishesOrdersRepository.findById(id).isPresent()){
            dishesOrdersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public DishesOrdersDTO getDishesOrdersById(Long id) {
        if (dishesOrdersRepository.findById(id).isPresent()){
            return dishesOrdersMapper.toDTO(dishesOrdersRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<DishesOrdersDTO> getAllDishesOrders() {
        if (!dishesOrdersRepository.findAll().isEmpty()){
            return dishesOrdersRepository.findAll().stream().map(dishesOrdersMapper::toDTO).toList();
        }
        return null;
    }

    @Override
    public DishesOrdersDTO getDishesOrdersByName(String name) {
        if (dishesOrdersRepository.findByDishes_Name(name) != null){
            return dishesOrdersMapper.toDTO(dishesOrdersRepository.findByDishes_Name(name));
        }
        return null;
    }
}
