package net.carlos.dev.backend.dto.dishes;

import lombok.Builder;
import lombok.Data;
import net.carlos.dev.backend.entities.dishes.CategoryDishes;

@Data
@Builder(toBuilder = true)
public class DishesDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryDishes categoryDishes;

}
