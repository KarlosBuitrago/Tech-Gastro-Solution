package net.carlos.dev.backend.mappers.dishes;

import net.carlos.dev.backend.dto.dishes.DishesDTO;
import net.carlos.dev.backend.entities.dishes.Dishes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishesMapper {
    DishesMapper INSTANCE = Mappers.getMapper(DishesMapper.class);

    Dishes toEntity(DishesDTO dishesDTO);
    DishesDTO toDTO(Dishes dishes);
}
