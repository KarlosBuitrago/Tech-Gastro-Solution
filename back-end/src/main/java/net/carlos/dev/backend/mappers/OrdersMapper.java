package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.orders.OrdersDTO;
import net.carlos.dev.backend.entities.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);
    OrdersDTO toDTO(Orders orders);
    Orders toEntity(OrdersDTO ordersDTO);
}
