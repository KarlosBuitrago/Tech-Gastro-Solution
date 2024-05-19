package net.carlos.dev.backend.service;

import net.carlos.dev.backend.dto.orders.OrdersDTO;

import java.util.List;

public interface IOrdersService {
    boolean saveOrder(OrdersDTO orderDTO);
    boolean updateOrder(OrdersDTO orderDTO);
    boolean deleteOrder(Long id);
    List<OrdersDTO> getAllOrders();
    OrdersDTO getOrderByOrderNumber(String orderNumber);
    List<OrdersDTO> getOrdersByStatus(String status);
    List<OrdersDTO> getOrdersByUsername(String username);
}
