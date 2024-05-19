package net.carlos.dev.backend.controllers;

import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.service.impl.IOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gastro-tech/api/v1")
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
}
