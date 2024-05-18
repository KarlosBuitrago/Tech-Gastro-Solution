package net.carlos.dev.backend.mappers.dishes;

import net.carlos.dev.backend.dto.dishes.CategoryDishesDTO;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CategoryDishesMapper {
    CategoryDishesMapper INSTANCE = Mappers.getMapper(CategoryDishesMapper.class);

    CategoryDishesDTO toDTO(CategoryDishes categoryDishes);

    CategoryDishes toEntity(CategoryDishesDTO categoryDishesDTO);
}
