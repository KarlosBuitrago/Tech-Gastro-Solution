package net.carlos.dev.backend.mappers;

import net.carlos.dev.backend.dto.orders.DishesOrdersDTO;
import net.carlos.dev.backend.entities.DishesOrders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DishesOrdersMapper {
    DishesOrdersMapper INSTANCE = Mappers.getMapper(DishesOrdersMapper.class);
    DishesOrdersDTO toDTO(DishesOrders dishesOrders);
    DishesOrders toEntity(DishesOrdersDTO dishesOrdersDTO);
}
