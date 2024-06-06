package net.carlos.dev.backend.controllers.orders;

import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.service.impl.orders.IOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "gastro-tech/api/v1/orders", produces = "application/json", consumes = "application/json")
public class OrdersController {

    @Autowired
    private IOrdersServiceImpl ordersService;

    @PostMapping("/order")
    public boolean saveOrder(@RequestBody OrdersDTO orderDTO){
        ordersService.saveOrder(orderDTO);
        System.out.println(orderDTO.getOrderNumber());
        List<OrdersDTO> orders = List.of(orderDTO);
        System.out.println(orders);
        return true;
    }

    @GetMapping("/orders")
    public List<OrdersDTO> getOrders(){
        return ordersService.getAllOrders();
    }

    @GetMapping("/order/{orderNumber}")
    public OrdersDTO getOrderByOrderNumber(@PathVariable String orderNumber){
        return ordersService.getOrderByOrderNumber(orderNumber);
    }

    @GetMapping("/status/{status}")
    public List<OrdersDTO> getOrdersByStatus(@PathVariable String status){
        return ordersService.getOrdersByStatus(status);
    }
    @GetMapping("/username/{username}")
    public List<OrdersDTO> getOrdersByUsername(@PathVariable String username){
        return ordersService.getOrdersByUsername(username);
    }

    @PutMapping("/order")
    public boolean updateOrder(@RequestBody OrdersDTO orderDTO){
        ordersService.updateOrder(orderDTO);
        return true;
    }

    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable Long id){
        ordersService.deleteOrder(id);
        return true;
    }

    @PatchMapping("/order/id/{id}")
    public boolean updateStatus(@PathVariable Long id, @RequestBody String status){
        return ordersService.updateStatus(id, status);
    }
}
