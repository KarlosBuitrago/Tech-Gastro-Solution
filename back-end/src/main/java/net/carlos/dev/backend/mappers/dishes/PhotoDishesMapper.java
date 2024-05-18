package net.carlos.dev.backend.mappers.dishes;

import net.carlos.dev.backend.dto.dishes.PhotoDishesDTO;
import net.carlos.dev.backend.entities.dishes.PhotoDishes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhotoDishesMapper {
    PhotoDishesMapper INSTANCE = Mappers.getMapper(PhotoDishesMapper.class);
    PhotoDishesDTO toDTO(PhotoDishes photoDishes);
    PhotoDishes toEntity(PhotoDishesDTO photoDishesDTO);
}
